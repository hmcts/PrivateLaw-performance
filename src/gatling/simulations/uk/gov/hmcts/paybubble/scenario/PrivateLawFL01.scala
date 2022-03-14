package uk.gov.hmcts.paybubble.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.paybubble.scenario.PrivateLawLogin.{baseURL, orgDomain}
import uk.gov.hmcts.paybubble.util.{CommonHeader, Environment, LoginHeader}

object PrivateLawFL01 {

  val IdamUrl = Environment.IdamURL
  val baseURL=Environment.baseURL
  val orgDomain=Environment.baseDomainOrg
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  //====================================================================================
  //CreateCase
  //=====================================================================================


  val Cases =

  group("PRL_010_Cases") {
    exec(http("PRL_010_005_Cases")
      .get(baseURL+"cases")
      .headers(CommonHeader.headers_Cases)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

      }

      .pause(MinThinkTime, MaxThinkTime)

  //==================================================================================
  //Business process : Click Create Case
  //==================================================================================
  val CreateCases =

  group("PRL_010_CreateCase") {
    exec(http("PRL_010_005_PRL_010_CreateCase")
      .get(baseURL+"aggregated/caseworkers/:uid/jurisdictions?access=create")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

      .exec(http("PRL_020_015_CreateCaseUserDetails")
        .get(baseURL + "/api/user/details")
        .headers(CommonHeader.headers_CreateCases)
        .check(status.in(200, 304)))

  }

    .pause(MinThinkTime, MaxThinkTime)

  //==================================================================================
  //Business process : Start Create Case
  //==================================================================================

  group("PRL_010_StartCreateCase") {
    exec(http("PRL_010_005_StartCreateCase")
      .get(baseURL+"cases/case-create/PRIVATELAW/PRLAPPS/solicitorCreate/solicitorCreate2")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed

      .group("PRL_010_StartCreateCaseFPL01") {
        exec(http("PRL_010_StartCreateCaseFPL01")
          .post("data/case-types/PRLAPPS/validate?pageId=solicitorCreate2")
          .headers(CommonHeader.headers_CreateCases)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01StartCreateCase.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)

    //==================================================================================
    //Business process : Confidentiality Statement
    //==================================================================================

      .group("PRL_010_Confidentiality Statement") {
        exec(http("PRL_010_Confidentiality Statement")
          .post("data/case-types/PRLAPPS/validate?pageId=solicitorCreate3")
          .headers(CommonHeader.headers_CreateCases)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01ConfidentialityStatement.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)



      //==================================================================================
      //Business process : SolifictorApplication CaseName
      //==================================================================================

      .group("PRL_010_Confidentiality Statement") {
        exec(http("PRL_010_Confidentiality Statement")
          .post("data/case-types/PRLAPPS/validate?pageId=solicitorCreate3")
          .headers(CommonHeader.headers_CreateCases)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01ConfidentialityStatement.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)

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