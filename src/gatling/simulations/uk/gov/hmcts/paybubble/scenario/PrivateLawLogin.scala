package uk.gov.hmcts.paybubble.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.paybubble.scenario.check.CsrfCheck
import uk.gov.hmcts.paybubble.scenario.check.CsrfCheck.{csrfParameter, csrfTemplate}
import uk.gov.hmcts.paybubble.util.{CommonHeader, Environment, LoginHeader}

object PrivateLawLogin {

  val IdamUrl = Environment.IdamURL
  val baseURL=Environment.baseURL
  val orgDomain=Environment.baseDomainOrg
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  //====================================================================================
  //Business process : Access Home Page by hitting the URL and relevant sub requests
  //below requests are Homepage and relevant sub requests as part of the login submission
  //=====================================================================================


  val homePage =

  group("PRL_010_Homepage") {
    exec(http("PRL_010_005_Homepage")
      .get(baseURL)
      .headers(LoginHeader.headers_0)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(http("PRL_010_010_HomepageConfigUI")
        .get(baseURL +"/external/configuration-ui")
        .headers(LoginHeader.headers_1))

      .exec(http("PRL_010_015_HomepageConfigJson")
        .get(baseURL +"/assets/config/config.json")
        .headers(LoginHeader.headers_1))

      .exec(http("PRL_010_020_HomepageTCEnabled")
        .get(baseURL +"/api/configuration?configurationKey=termsAndConditionsEnabled")
        .headers(LoginHeader.headers_1))

      .exec(http("PRL_010_025_HomepageIsAuthenticated")
        .get(baseURL +"/auth/isAuthenticated")
        .headers(LoginHeader.headers_1))

      .exec(http("PRL_010_030_AuthLogin")
        .get(baseURL +"/auth/login")
        .headers(LoginHeader.headers_4)
        .check(css("input[name='_csrf']", "value").saveAs("csrfToken"))
        //.check(regex("manage-user%20create-user&state=(.*)&client").saveAs("state")))
        .check(regex("/oauth2/callback&amp;state=(.*)&amp;nonce=").saveAs("state"))
        .check(regex("&nonce=(.*)&response_type").saveAs("nonce")))

      }

      .pause(MinThinkTime, MaxThinkTime)

  //==================================================================================
  //Business process : Enter the login details and submit
  //below requests are main login and relevant sub requests as part of the login submission
  //==================================================================================

  val PRLLogin =

    group("PRL_020_SignIn") {
      exec(flushHttpCache).exec(http("PRL_020_005_SignIn")
        //.post(IdamUrl + "/login?response_type=code&redirect_uri=https%3A%2F%2F" + orgDomain + "%2Foauth2%2Fcallback&scope=profile%20openid%20roles%20manage-user%20create-user%20manage-roles&state=${state}&client_id=xuimowebapp")
       // .post(IdamUrl + "/login?client_id=xuiwebapp&redirect_uri="+baseURL+"oauth2/callback&state=${state}&nonce=${nonce}&response_type=code&scope=profile")
        .post(IdamUrl + "/login?client_id=xuiwebapp&redirect_uri="+baseURL+"oauth2/callback&state=${state}&nonce=${nonce}&response_type=code&scope=profile%20openid%20roles%20manage-user%20create-user%20search-user&prompt=")
        .formParam("username", "fprl_caseworker_solicitor@mailinator.com")
        .formParam("password", "Pass19word")
        .formParam("save", "Sign in")
        .formParam("selfRegistrationEnabled", "false")
        .formParam("_csrf", "${csrfToken}")
        .headers(LoginHeader.headers_login_submit)
        .check(status.in(200, 304, 302))).exitHereIfFailed

       .exec(getCookieValue(CookieKey("__auth__").withDomain("manage-case.aat.platform.hmcts.net").saveAs("authTokenResp")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

        .exec(http("PRL_020_010_Homepage")
          .get(baseURL + "/external/config/ui")
          .headers(LoginHeader.headers_0)
          .check(status.in(200, 304)))

      //  https://manage-case.aat.platform.hmcts.net/external/config/ui

        .exec(http("PRL_020_015_Homepage")
          .get(baseURL + "/api/user/details")
          .headers(LoginHeader.headers_manageorglogin)
          .check(status.in(200, 304)))

        //https://manage-case.aat.platform.hmcts.net/api/user/details

        .exec(http("PRL_020_020_SignInTCEnabled")
          .get("/auth/isAuthenticated")
          .headers(LoginHeader.headers_manageorglogin)
          .check(status.in(200, 304)))

        //https://manage-case.aat.platform.hmcts.net/api/user/details

        .exec(http("PRL_020_025_SignInTCEnabled")
          .get(baseURL +"/api/configuration?configurationKey=termsAndConditionsEnabled")
          .headers(LoginHeader.headers_manageorglogin)
          .check(status.in(200, 304)))

        //https://manage-case.aat.platform.hmcts.net/api/configuration?configurationKey=termsAndConditionsEnabled


        .exec(http("PRL_020_030_APIOrg")
          .get(baseURL + "/api/organisation")
          .headers(LoginHeader.headers_manageorglogin)
          .check(status.in(200, 304)))

        //https://manage-case.aat.platform.hmcts.net/api/organisation
    }

    .exitHereIfFailed
    .pause(MinThinkTime , MaxThinkTime)


  //==================================================================================
  //Business process : Log out of Paybubble
  //==================================================================================

  val logout =
  group("PaymentAPI${service}_${SignoutNumber}_Logout"){
    exec(http("PaymentAPI${service}_${SignoutNumber}_010_Logout")
    .get("/logout")
      .headers(CommonHeader.headers_logout)
      .check(regex("This page cannot be found"))
      .check(status.is(404)))
  }

}