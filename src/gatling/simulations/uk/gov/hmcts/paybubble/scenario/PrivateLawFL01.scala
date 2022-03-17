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

//ad_001_Title
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
  /*
      .exec(http("PRL_020_015_CreateCaseUserDetails")
        .get(baseURL + "/api/user/details")
        .headers(CommonHeader.headers_CreateCases)
        .check(status.in(200, 304)))
  */
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
  }

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

      group("PRL_010_Fl100 WithoutNoticeOrderDetails4") {
        exec(http("PRL_010_Fl100 WithoutNoticeOrderDetails4")
          .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=withoutNoticeOrderDetails4")
          .headers(CommonHeader.headers_WithoutNotice)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01WithoutNoticeOrderDetails4.json")))
      }

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

      //Save and continue

      group("PRL_010_Fl100 WithoutNoticeOrderDetails5") {
        exec(http("PRL_010_Fl100 WithoutNoticeOrderDetails5")
          .post(baseURL + "/data/cases/1647252224099105/events")
          .headers(CommonHeader.headers_WithoutNotice)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01WithoutNoticeOrderDetails5.json")))
      }

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

      group("PRL_010_Fl100 WithoutNoticeOrderDetails6") {
        exec(http("PRL_010_Fl100 WithoutNoticeOrderDetails6")
          .post(baseURL + "/workallocation/searchForCompletable")
          .headers(CommonHeader.headers_WithoutNotice)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01WithoutNoticeOrderDetails6.json")))
      }

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

      group("PRL_010_WithoutNoticeOrderDetails7") {
        exec(http("PRL_010_WithoutNoticeOrderDetails7")
          .get(baseURL + "/data/internal/cases/1647252224099105")
          .headers(CommonHeader.headers_WithoutNotice)
          .check(status.in(200, 304))).exitHereIfFailed

          .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
      }
    }
        //ApplicantDetails
        val ApplicantDetails =
          group("PRL_010_ApplicantDetails") {
            exec(http("PRL_010_ApplicantDetails")
              .get(baseURL + "/cases/case-details/1647252224099105/trigger/applicantsDetails/applicantsDetails1")
              .headers(CommonHeader.headers_ApplicantDetails)
              .check(status.in(200, 304))).exitHereIfFailed

              .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
          }
            group("PRL_010_Fl100 ApplicantDetails2") {
              exec(http("PRL_010_Fl100 ApplicantDetails2")
                .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=applicantsDetails2")
                .headers(CommonHeader.headers_ApplicantDetails)
                .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
                .header("x-xsrf-token", "${XSRFToken}")
                .body(ElFileBody("FPL01ApplicantDetails2.json")))
            }

              .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_Fl100 ApplicantDetails3") {
    exec(http("PRL_010_Fl100 ApplicantDetails3")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ApplicantDetails3.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_Fl100 ApplicantDetails4") {
    exec(http("PRL_010_Fl100 ApplicantDetails4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ApplicantDetails4.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))


  group("PRL_010_Fl100 ApplicantDetails5") {
    exec(http("PRL_010_Fl100 ApplicantDetails5")
      .get(baseURL + "/data/internal/cases/1647252224099105")
      .headers(CommonHeader.headers_ApplicantDetails)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

//Respondent Details
val RespondentDetails =
  group("PRL_010_RespondentDetails") {
    exec(http("PRL_010_RespondentDetails")
      .get(baseURL + "/cases/case-details/1647252224099105/trigger/respondentsDetails/respondentsDetails1")
      .headers(CommonHeader.headers_ApplicantDetails)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_010_RespondentDetails2") {
    exec(http("PRL_010_RespondentDetails2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentsDetails2")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RespondentDetails2.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_RespondentDetails3") {
    exec(http("PRL_010_RespondentDetails3")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RespondentDetails3.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_RespondentDetails4") {
    exec(http("PRL_010_RespondentDetails4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RespondentDetails4.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))



  //Applicants Family
  val RespondentDetails =
    group("PRL_010_ApplicantDetails") {
      exec(http("PRL_010_ApplicantDetails")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401ApplicantFamilyDetails/fl401ApplicantFamilyDetails1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_010_ApplicantsFamily2") {
    exec(http("PRL_010_ApplicantsFamily2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401ApplicantFamilyDetails1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("ApplicantsFamily2.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_ApplicantsFamily3") {
    exec(http("PRL_010_ApplicantsFamily3")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("ApplicantsFamily3.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_ApplicantsFamily4") {
    exec(http("PRL_010_ApplicantsFamily4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("ApplicantsFamily4.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  //relationship to respondent
  val Relationshiptorespondent =
    group("PRL_010_Relationshiptorespondent") {
      exec(http("PRL_010_Relationshiptorespondent")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/respondentRelationship/respondentRelationship1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_010_Relationshiptorespondent1") {
    exec(http("PRL_010_Relationshiptorespondent1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentRelationship1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_Relationshiptorespondent2") {
    exec(http("PRL_010_Relationshiptorespondent2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentRelationship2")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_Relationshiptorespondent3") {
    exec(http("PRL_010_Relationshiptorespondent3")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_Relationshiptorespondent4") {
    exec(http("PRL_010_Relationshiptorespondent4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent4.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))


  //Respondent Behavior

  val RespondentBehvaiour =
    group("PRL_010_RespondentBehvaiour") {
      exec(http("PRL_010_RespondentBehvaiour")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/respondentBehaviour/respondentBehaviour1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_010_RespondentBehaviour1") {
    exec(http("PRL_010_RespondentBehaviour1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentBehaviour1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_RespondentBehaviour2") {
    exec(http("PRL_010_RespondentBehaviour2")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_RespondentBehaviour3") {
    exec(http("PRL_010_RespondentBehaviour3")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  //the home
  val TheHome =
    group("PRL_010_TheHome") {
      exec(http("PRL_010_TheHome")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401Home/fl401Home1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_010_TheHome1") {
    exec(http("PRL_010_TheHome1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401Home1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01TheHome1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_TheHome2") {
    exec(http("PRL_010_TheHome2")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01TheHome2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_TheHome2") {
    exec(http("PRL_010_TheHome3")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

//Other Proceedings

  val OtherProceedings =
    group("PRL_010_OtherProceedings") {
      exec(http("PRL_010_OtherProceedings")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401OtherProceedings/fl401OtherProceedings1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_010_OtherProceedings1") {
    exec(http("PRL_010_OtherProceedings1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401OtherProceedings1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01OtherProceedings1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_OtherProceedings2") {
    exec(http("PRL_010_OtherProceedings2")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01OtherProceedings2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_010_OtherProceedings3") {
    exec(http("PRL_010_OtherProceedings3")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01OtherProceedings3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  //Attending Hearing

  val AttendingHearing =
    group("PRL_010_AttendingHearing") {
      exec(http("PRL_010_AttendingHearing")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/attendingTheHearing/attendingTheHearing1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_010_AttendingHearing1") {
    exec(http("PRL_010_AttendingHearing1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=attendingTheHearing1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01AttendingHearing1.json")))

    group("PRL_010_AttendingHearing2") {
      exec(http("PRL_010_AttendingHearing2")
        .post(baseURL + "/data/cases/1647252224099105/events")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01AttendingHearing2.json")))

    group("PRL_010_AttendingHearing3") {
      exec(http("PRL_010_AttendingHearing3")
        .post(baseURL + "/workallocation/searchForCompletable")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01AttendingHearing2.json")))

  //Welsh Language

      val WelshLanguage =
        group("PRL_010_Welsh") {
          exec(http("PRL_010_Welsh")
            .get(baseURL + "/cases/case-details/1647252224099105/trigger/welshLanguageRequirements/welshLanguageRequirements1")
            .headers(CommonHeader.headers_ApplicantsFamiliy)
            .check(status.in(200, 304))).exitHereIfFailed

            .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
        }

      group("PRL_010_Welsh1") {
        exec(http("PRL_010_PRL_010_Welsh1")
          .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=welshLanguageRequirements1")
          .headers(CommonHeader.headers_ApplicantsFamiliy)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01Welsh1.json")))

      group("PRL_010_Welsh2") {
        exec(http("PRL_010_PRL_010_Welsh2")
          .post(baseURL + "/data/cases/1647252224099105/events")
          .headers(CommonHeader.headers_ApplicantsFamiliy)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01Welsh2.json")))

      group("PRL_010_Welsh3") {
        exec(http("PRL_010_PRL_010_Welsh3")
          .post(baseURL + "/workallocation/searchForCompletable")
          .headers(CommonHeader.headers_ApplicantsFamiliy)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01Welsh3.json")))


  //Upload documents

  val UploadDocuments =
    group("PRL_010_UploadDoc") {
      exec(http("PRL_010_UploadDoc")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401UploadDocuments/fl401UploadDocuments1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

        group("PRL_010_UploadDoc1") {
          exec(http("PRL_010_UploadDoc1")
            .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401UploadDocuments1")
            .headers(CommonHeader.headers_ApplicantsFamiliy)
            .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
            .header("x-xsrf-token", "${XSRFToken}")
            .body(ElFileBody("FPL01UploadDoc1.json")))


          group("PRL_010_UploadDoc2") {
            exec(http("PRL_010_UploadDoc2")
              .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401UploadDocuments2")
              .headers(CommonHeader.headers_ApplicantsFamiliy)
              .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
              .header("x-xsrf-token", "${XSRFToken}")
              .body(ElFileBody("FPL01UploadDoc2.json")))

            group("PRL_010_UploadDoc3") {
              exec(http("PRL_010_UploadDoc3")
                .post(baseURL + "/data/cases/1647252224099105/events")
                .headers(CommonHeader.headers_ApplicantsFamiliy)
                .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
                .header("x-xsrf-token", "${XSRFToken}")
                .body(ElFileBody("FPL01UploadDoc3.json")))

            group("PRL_010_UploadDoc4") {
                exec(http("PRL_010_UploadDoc4")
                  .post(baseURL + "/workallocation/searchForCompletable")
                  .headers(CommonHeader.headers_ApplicantsFamiliy)
                  .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
                  .header("x-xsrf-token", "${XSRFToken}")
                  .body(ElFileBody("FPL01UploadDoc4.json")))


    //==================================================================================
    //Business process : Log out of Paybubble
    //==================================================================================
/*
    val logout =
      group("PaymentAPI${service}_${SignoutNumber}_Logout") {
        exec(http("PaymentAPI${service}_${SignoutNumber}_010_Logout")
          .get("/logout")
          .headers(CommonHeader.headers_logout)
          .check(regex("This page cannot be found"))
          .check(status.is(404)))
      }


 */

}
