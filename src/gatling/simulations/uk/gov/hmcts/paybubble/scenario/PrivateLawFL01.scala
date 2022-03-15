package uk.gov.hmcts.paybubble.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.paybubble.scenario.PrivateLawLogin.{baseURL, orgDomain}
import uk.gov.hmcts.paybubble.util.{CommonHeader, Environment, LoginHeader}

object PrivateLawFL01 {

  val IdamUrl = Environment.IdamURL
  val baseURL = Environment.baseURL
  val orgDomain = Environment.baseDomainOrg
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  //====================================================================================
  //CreateCase
  //=====================================================================================


  val Cases =

    group("PRL_010_Cases") {
      exec(http("PRL_010_005_Cases")
        .get(baseURL + "cases")
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
      .get(baseURL + "aggregated/caseworkers/:uid/jurisdictions?access=create")
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
      .get(baseURL + "cases/case-create/PRIVATELAW/PRLAPPS/solicitorCreate/solicitorCreate2")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed

      .group("PRL_010_StartCreateCaseFPL01") {
        exec(http("PRL_010_StartCreateCaseFPL01")
          .post(baseURL + "data/case-types/PRLAPPS/validate?pageId=solicitorCreate2")
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
          .post(baseURL + "data/case-types/PRLAPPS/validate?pageId=solicitorCreate3")
          .headers(CommonHeader.headers_CreateCases)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01ConfidentialityStatement.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)



      //==================================================================================
      //Business process : SolicitorApplication CaseName
      //==================================================================================

      .group("PRL_010_Fl100 CaseName") {
        exec(http("PRL_010_Fl100 CaseName")
          .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=solicitorCreate5")
          .headers(CommonHeader.headers_CreateCases)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01CaseNameSaveandContinue.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)


      //==================================================================================
      //Business process : SolicitorApplication CaseName Save and Continue
      //==================================================================================

      .group("PRL_010_Fl100 CaseNameSaveandContinue") {
        exec(http("PRL_010_Fl100 CaseNameSaveandContinue")
          .post(baseURL + "/data/case-types/PRLAPPS/cases?ignore-warning=false")
          .headers(CommonHeader.headers_CreateCases)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01CaseNameSaveandContinue.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)

      //==================================================================================
      //Business process : SolicitorApplication CaseName Save and Continue
      //Need to capture ccid value
      //==================================================================================

      .group("PRL_010_Fl100 CaseNameSaveandContinue") {
        exec(http("PRL_010_Fl100 CaseNameSaveandContinueSearchForCompletable")
          .post(baseURL + "/workallocation/searchForCompletable")
          .headers(CommonHeader.headers_CreateCases)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01CaseNameSearchforCompletable.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)
  }
  //==================================================================================
  //Business process : Type of Application
  //==================================================================================
  val TypeofApplication =

  group("PRL_010_TypeofApplication") {
    exec(http("PRL_010_005_PRL_010_TypeofApplication")
      .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401TypeOfApplication/fl401TypeOfApplication1")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
  group("PRL_010_TypeofApplicationConfigUI") {
    exec(http("PRL_010_005_PRL_010_TypeofApplicationConfigUI")
      .get(baseURL + "/external/configuration-ui/")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_TypeofApplicationTermsandConditions") {
    exec(http("PRL_010_005_PRL_010_TypeofApplicationTermsandConditions")
      .get(baseURL + "/api/configuration?configurationKey=termsAndConditionsEnabled")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_TypeofApplicationUserDetails") {
    exec(http("PRL_010_005_PRL_010_TypeofApplicationUserDetails")
      .get(baseURL + "/api/user/details")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_TypeofApplicationIsAuthenticated") {
    exec(http("PRL_010_005_PRL_010_TypeofApplicationIsAuthenticated")
      .get(baseURL + "/auth/isAuthenticated")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  val TypeofApplicationOrders =

    group("PRL_010_TypeofApplicationOrders") {
      exec(http("PRL_010_Fl100 TypeofApplicationOrders")
        .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401TypeOfApplication1")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("TypeOfApplication.json")))
    }

  group("PRL_010_TypeofApplicationOrders2") {
    exec(http("PRL_010_Fl100 TypeofApplicationOrders2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401TypeOfApplication2")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("TypeOfApplication2.json")))
  }

  //ccid needs to be captured and passed
  group("PRL_010_TypeofApplicationOrderSaveandContinue") {
    exec(http("PRL_010_TypeofApplicationOrderSaveandContinue")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("TypeOfApplication2.json")))
  }


 //Without Notice Order
  val WithoutNoticeOrder =
    group("PRL_010_WithoutNoticeOrder") {
      exec(http("PRL_010_005_PRL_010_WithoutNoticeOrder")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails1")
        .headers(CommonHeader.headers_WithoutNotice)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))


      group("PRL_010_Fl100 WithoutNoticeOrderDetails1") {
        exec(http("PRL_010_Fl100 WithoutNoticeOrderDetails1")
          .post(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails1")
          .headers(CommonHeader.headers_WithoutNotice)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01WithoutNotice.json")))
      }

      group("PRL_010_Fl100 WithoutNoticeOrderDetails2") {
        exec(http("PRL_010_Fl100 WithoutNoticeOrderDetails2")
          .post(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails2")
          .headers(CommonHeader.headers_WithoutNotice)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01WithoutNoticeOrderDetails2.json")))
      }

            .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

      group("PRL_010_Fl100 WithoutNoticeOrderDetails3") {
        exec(http("PRL_010_Fl100 WithoutNoticeOrderDetails3")
          .post(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails3")
          .headers(CommonHeader.headers_WithoutNotice)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01WithoutNoticeOrderDetails3.json")))
      }

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

      group("PRL_010_Fl100 WithoutNoticeOrderDetails3") {
        exec(http("PRL_010_Fl100 WithoutNoticeOrderDetails3")
          .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=withoutNoticeOrderDetails4")
          .headers(CommonHeader.headers_WithoutNotice)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01WithoutNoticeOrderDetails3.json")))
      }

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

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