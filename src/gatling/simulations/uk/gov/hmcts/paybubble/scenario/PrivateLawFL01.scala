package uk.gov.hmcts.paybubble.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.paybubble.util.{CommonHeader, CommonHeaderC100, Environment, LoginHeader}
import net.sf.saxon.functions.BaseUri_1
import uk.gov.hmcts.paybubble.scenario.PrivateLawLogin.{baseURL, orgDomain}

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
      exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
      .exec(getCookieValue(CookieKey("__auth__").withDomain("manage-case.aat.platform.hmcts.net").saveAs("authTokenResp")))

    }

      .pause(MinThinkTime, MaxThinkTime)

  //==================================================================================
  //Business process : Click Create Case
  //==================================================================================
  val CreateCases =


  group("PRL_FL01_010_CreateCase") {
    exec(http("PRL_FPL_010_CreateCase")
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

  .group("PRL_FL01_020_StartCreateCase") {
    exec(http("PRL_FPL_020_CreateCase")
      .get(baseURL + "cases/case-create/PRIVATELAW/PRLAPPS/solicitorCreate/solicitorCreate2")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
  }

  .group("PRL_FL01_030_StartCreateCaseFL01") {
    exec(http("PRL_FPL_030_StartCreateCaseFPL01")
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

  .group("PRL_FL01_040_Confidentiality_Statement") {
    exec(http("PRL_FL01_040_Confidentiality Statement")
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

  .group("PRL_FL01_050_CaseName") {
    exec(http("PRL_FL01_050_CaseName")
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

  .group("PRL_FL01_060_CaseNameSaveandContinue") {
    exec(http("PRL_FL01_060_CaseNameSaveandContinue")
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

  .group("PRL_FL01_070_CaseNameSaveandContinue2") {
    exec(http("PRL_FL01_070_CaseNameSaveandContinue2")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01CaseNameSearchforCompletable.json")))
  }
  .pause(MinThinkTime, MaxThinkTime)

  //==================================================================================
  //Business process : Type of Application
  //==================================================================================
  val TypeofApplication =

  group("PRL_FL01_080_TypeOfApplication1") {
    exec(http("PRL_FL01_080_TypeOfApplication1")
      .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401TypeOfApplication/fl401TypeOfApplication1")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_FL01_081_TypeofApplicationConfigUI") {
    exec(http("PRL_FL01_081_TypeofApplicationConfigUI")
      .get(baseURL + "/external/configuration-ui/")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_082_TypeofApplicationTermsandConditions") {
    exec(http("PRL_FL01_082_TypeofApplicationTermsandConditions")
      .get(baseURL + "/api/configuration?configurationKey=termsAndConditionsEnabled")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_083_TypeofApplicationUserDetails") {
    exec(http("PRL_FL01_083_TypeofApplicationUserDetails")
      .get(baseURL + "/api/user/details")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_084_TypeofApplicationIsAuthenticated") {
    exec(http("PRL_FL01_084_TypeofApplicationIsAuthenticated")
      .get(baseURL + "/auth/isAuthenticated")
      .headers(CommonHeader.headers_TypeofApplication)
      .check(status.in(200, 304))).exitHereIfFailed

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  val TypeofApplicationOrders =

    group("PRL_FL01_090_TypeofApplicationOrders") {
      exec(http("PRL_FL01_090_TypeofApplicationOrders")
        .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401TypeOfApplication1")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("TypeOfApplication.json")))
    }

  group("PRL_FL01_100_TypeofApplicationOrders2") {
    exec(http("PRL_FL01_100_TypeofApplicationOrders2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401TypeOfApplication2")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("TypeOfApplication2.json")))
  }

  //ccid needs to be captured and passed
  group("PRL_FL01_110_TypeofApplicationOrderSaveandContinue") {
    exec(http("PRL_FL01_110_TypeofApplicationOrderSaveandContinue")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("TypeOfApplication2.json")))
  }


 //Without Notice Order
  val WithoutNoticeOrder =
    group("PRL_FL01_120_WithoutNoticeOrder") {
      exec(http("PRL_FL01_120_WithoutNoticeOrder")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails1")
        .headers(CommonHeader.headers_WithoutNotice)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }


    group("PRL_FL01_130_WithoutNoticeOrderDetails1") {
      exec(http("PRL_FL01_130_WithoutNoticeOrderDetails1")
        .post(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails1")
        .headers(CommonHeader.headers_WithoutNotice)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01WithoutNotice.json")))
    }

    group("PRL_FL01_140_WithoutNoticeOrderDetails2") {
      exec(http("PRL_FL01_140_WithoutNoticeOrderDetails2")
        .post(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails2")
        .headers(CommonHeader.headers_WithoutNotice)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01WithoutNoticeOrderDetails2.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    group("PRL_FL01_150_WithoutNoticeOrderDetails3") {
      exec(http("PRL_FL01_150_WithoutNoticeOrderDetails3")
        .post(baseURL + "/cases/case-details/1647252224099105/trigger/withoutNoticeOrderDetails/withoutNoticeOrderDetails3")
        .headers(CommonHeader.headers_WithoutNotice)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01WithoutNoticeOrderDetails3.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    group("PRL_FL01_160_WithoutNoticeOrderDetails4") {
      exec(http("PRL_FL01_160_WithoutNoticeOrderDetails4")
        .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=withoutNoticeOrderDetails4")
        .headers(CommonHeader.headers_WithoutNotice)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01WithoutNoticeOrderDetails4.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    //Save and continue

    group("PRL_FL01_170_WithoutNoticeOrderDetails5") {
      exec(http("PRL_FL01_170_WithoutNoticeOrderDetails5")
        .post(baseURL + "/data/cases/1647252224099105/events")
        .headers(CommonHeader.headers_WithoutNotice)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01WithoutNoticeOrderDetails5.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    group("PRL_FL01_171_WithoutNoticeOrderDetails6") {
      exec(http("PRL_FL01_171_WithoutNoticeOrderDetails6")
        .post(baseURL + "/workallocation/searchForCompletable")
        .headers(CommonHeader.headers_WithoutNotice)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01WithoutNoticeOrderDetails6.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    group("PRL_FL01_172_WithoutNoticeOrderDetails7") {
      exec(http("PRL_FL01_172_WithoutNoticeOrderDetails7")
        .get(baseURL + "/data/internal/cases/1647252224099105")
        .headers(CommonHeader.headers_WithoutNotice)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  //ApplicantDetails
  val ApplicantDetails =
    group("PRL_FL01_180_ApplicantDetails") {
      exec(http("PRL_FL01_180_ApplicantDetails")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/applicantsDetails/applicantsDetails1")
        .headers(CommonHeader.headers_ApplicantDetails)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

    group("PRL_FL01_190_ApplicantDetails2") {
      exec(http("PRL_FL01_190_ApplicantDetails2")
        .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=applicantsDetails2")
        .headers(CommonHeader.headers_ApplicantDetails)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01ApplicantDetails2.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    group("PRL_FL01_200_ApplicantDetails3") {
      exec(http("PRL_FL01_200_ApplicantDetails3")
        .post(baseURL + "/data/cases/1647252224099105/events")
        .headers(CommonHeader.headers_ApplicantDetails)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01ApplicantDetails3.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    group("PRL_FL01_201_ApplicantDetails4") {
      exec(http("PRL_FL01_201_ApplicantDetails4")
        .post(baseURL + "/workallocation/searchForCompletable")
        .headers(CommonHeader.headers_ApplicantDetails)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01ApplicantDetails4.json")))
    }

      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))


    group("PRL_FL01_202_ApplicantDetails5") {
      exec(http("PRL_FL01_202_Fl100 ApplicantDetails5")
        .get(baseURL + "/data/internal/cases/1647252224099105")
        .headers(CommonHeader.headers_ApplicantDetails)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  //Respondent Details
  val RespondentDetails =
    group("PRL_FL01_210_RespondentDetails") {
      exec(http("PRL_FL01_210_RespondentDetails")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/respondentsDetails/respondentsDetails1")
        .headers(CommonHeader.headers_ApplicantDetails)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_FL01_220_RespondentDetails2") {
    exec(http("PRL_FL01_220_RespondentDetails2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentsDetails2")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RespondentDetails2.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_230_RespondentDetails3") {
    exec(http("PRL_FL01_230_RespondentDetails3")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RespondentDetails3.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_231_RespondentDetails4") {
    exec(http("PRL_FL01_231_RespondentDetails4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantDetails)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RespondentDetails4.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))



  //Applicants Family
  val RespondentDetails2 =
    group("PRL_FL01_240_ApplicantDetails") {
      exec(http("PRL_FL01_240_ApplicantDetails")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401ApplicantFamilyDetails/fl401ApplicantFamilyDetails1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_FL01_250_ApplicantsFamily2") {
    exec(http("PRL_FL01_250_ApplicantsFamily2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401ApplicantFamilyDetails1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("ApplicantsFamily2.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_260_ApplicantsFamily3") {
    exec(http("PRL_FL01_260_ApplicantsFamily3")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("ApplicantsFamily3.json")))
  }

    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_261_ApplicantsFamily4") {
    exec(http("PRL_FL01_261_ApplicantsFamily4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("ApplicantsFamily4.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  //relationship to respondent
  val Relationshiptorespondent =
    group("PRL_FL01_270_Relationshiptorespondent") {
      exec(http("PRL_FL01_270_Relationshiptorespondent")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/respondentRelationship/respondentRelationship1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_FL01_280_Relationshiptorespondent1") {
    exec(http("PRL_FL01_280_Relationshiptorespondent1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentRelationship1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_290_Relationshiptorespondent2") {
    exec(http("PRL_FL01_290_Relationshiptorespondent2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentRelationship2")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_300_Relationshiptorespondent3") {
    exec(http("PRL_FL01_300_Relationshiptorespondent3")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_301_Relationshiptorespondent4") {
    exec(http("PRL_FL01_301_Relationshiptorespondent4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01RelationshiptoRespondent4.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))


  //Respondent Behavior

  val RespondentBehvaiour =
    group("PRL_FL01_310_RespondentBehvaiour") {
      exec(http("PRL_FL01_310_RespondentBehvaiour")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/respondentBehaviour/respondentBehaviour1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_FL01_320_RespondentBehaviour1") {
    exec(http("PRL_FL01_320_RespondentBehaviour1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentBehaviour1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_330_RespondentBehaviour2") {
    exec(http("PRL_FL01_330_RespondentBehaviour2")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_331_RespondentBehaviour3") {
    exec(http("PRL_FL01_331_RespondentBehaviour3")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  //the home
  val TheHome =
    group("PRL_FL01_340_TheHome") {
      exec(http("PRL_FL01_340_TheHome")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401Home/fl401Home1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_FL01_350_TheHome1") {
    exec(http("PRL_FL01_350_TheHome1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401Home1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01TheHome1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_360_TheHome2") {
    exec(http("PRL_FL01_360_TheHome2")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01TheHome2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_361_TheHome2") {
    exec(http("PRL_FL01_361_TheHome3")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01ResondentBehaviour3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

//Other Proceedings

  val OtherProceedings =
    group("PRL_FL01_370_OtherProceedings") {
      exec(http("PRL_FL01_370_OtherProceedings")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401OtherProceedings/fl401OtherProceedings1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_FL01_380_OtherProceedings1") {
    exec(http("PRL_FL01_380_OtherProceedings1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401OtherProceedings1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01OtherProceedings1.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_390_OtherProceedings2") {
    exec(http("PRL_FL01_390_OtherProceedings2")
      .post(baseURL + "/data/cases/1647252224099105/events")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01OtherProceedings2.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  group("PRL_FL01_391_OtherProceedings3") {
    exec(http("PRL_FL01_391_OtherProceedings3")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01OtherProceedings3.json")))
  }
    .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

  //Attending Hearing

  val AttendingHearing =
    group("PRL_FL01_400_AttendingHearing") {
      exec(http("PRL_FL01_400_AttendingHearing")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/attendingTheHearing/attendingTheHearing1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_FL01_410_AttendingHearing1") {
    exec(http("PRL_FL01_410_AttendingHearing1")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=attendingTheHearing1")
      .headers(CommonHeader.headers_ApplicantsFamiliy)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("FPL01AttendingHearing1.json")))
  }

    group("PRL_FL01_420_AttendingHearing2") {
      exec(http("PRL_FL01_420__AttendingHearing2")
        .post(baseURL + "/data/cases/1647252224099105/events")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01AttendingHearing2.json")))
    }

    group("PRL_FL01_421_AttendingHearing3") {
      exec(http("PRL_FL01_421__AttendingHearing3")
        .post(baseURL + "/workallocation/searchForCompletable")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("FPL01AttendingHearing2.json")))
    }

  //Welsh Language

      val WelshLanguage =
        group("PRL_FL01_430_Welsh") {
          exec(http("PRL_FL01_430_Welsh")
            .get(baseURL + "/cases/case-details/1647252224099105/trigger/welshLanguageRequirements/welshLanguageRequirements1")
            .headers(CommonHeader.headers_ApplicantsFamiliy)
            .check(status.in(200, 304))).exitHereIfFailed

            .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
        }

      group("PRL_FL01_440_Welsh1") {
        exec(http("PRL_FL01_440_Welsh1")
          .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=welshLanguageRequirements1")
          .headers(CommonHeader.headers_ApplicantsFamiliy)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01Welsh1.json")))
      }

      group("PRL_FL01_450_Welsh2") {
        exec(http("PRL_FL01_450_Welsh2")
          .post(baseURL + "/data/cases/1647252224099105/events")
          .headers(CommonHeader.headers_ApplicantsFamiliy)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01Welsh2.json")))
      }

      group("PRL_FL01_451_Welsh3") {
        exec(http("PRL_FL01_451_Welsh3")
          .post(baseURL + "/workallocation/searchForCompletable")
          .headers(CommonHeader.headers_ApplicantsFamiliy)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("FPL01Welsh3.json")))
      }


  //Upload documents

  val UploadDocuments =
    group("PRL_FL01_460_UploadDoc") {
      exec(http("PRL_FL01_460_UploadDoc")
        .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401UploadDocuments/fl401UploadDocuments1")
        .headers(CommonHeader.headers_ApplicantsFamiliy)
        .check(status.in(200, 304))).exitHereIfFailed

        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

        group("PRL_FL01_470_UploadDoc1") {
          exec(http("PRL_FL01_470_UploadDoc1")
            .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401UploadDocuments1")
            .headers(CommonHeader.headers_ApplicantsFamiliy)
            .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
            .header("x-xsrf-token", "${XSRFToken}")
            .body(ElFileBody("FPL01UploadDoc1.json")))
        }


          group("PRL_FL01_480_UploadDoc2") {
            exec(http("PRL_FL01_480_UploadDoc2")
              .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401UploadDocuments2")
              .headers(CommonHeader.headers_ApplicantsFamiliy)
              .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
              .header("x-xsrf-token", "${XSRFToken}")
              .body(ElFileBody("FPL01UploadDoc2.json")))
          }

            group("PRL_FL01_490_UploadDoc3") {
              exec(http("PRL_FL01_490_UploadDoc3")
                .post(baseURL + "/data/cases/1647252224099105/events")
                .headers(CommonHeader.headers_ApplicantsFamiliy)
                .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
                .header("x-xsrf-token", "${XSRFToken}")
                .body(ElFileBody("FPL01UploadDoc3.json")))
            }

            group("PRL_FL01_491_UploadDoc4") {
              exec(http("PRL_FL01_491_UploadDoc4")
                .post(baseURL + "/workallocation/searchForCompletable")
                .headers(CommonHeader.headers_ApplicantsFamiliy)
                .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
                .header("x-xsrf-token", "${XSRFToken}")
                .body(ElFileBody("FPL01UploadDoc4.json")))
            }


  val StatementOfTruth =
  group("PRL_FL01_500_SoT") {
  exec(http("PRL_FL01_500_SoT")
  .get(baseURL + "/cases/case-details/1647252224099105/trigger/fl401StatementOfTruthAndSubmit/fl401StatementOfTruthAndSubmit1")
  .headers(CommonHeader.headers_ApplicantsFamiliy)
  .check(status.in(200, 304))).exitHereIfFailed

  .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
}

  group("PRL_FL01_510_SoT1") {
  exec (http ("PRL_FL01_510_SoT1")
  .post (baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401StatementOfTruthAndSubmit1")
  .headers (CommonHeader.headers_ApplicantsFamiliy)
  .header ("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
  .header ("x-xsrf-token", "${XSRFToken}")
  .body (ElFileBody ("FPL01SoT1.json") ) )
}

  group("PRL_FL01_520_SoT2") {
  exec (http ("PRL_FL01_520_SoT2")
  .post (baseURL + "/data/case-types/PRLAPPS/validate?pageId=fl401StatementOfTruthAndSubmit2")
  .headers (CommonHeader.headers_ApplicantsFamiliy)
  .header ("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
  .header ("x-xsrf-token", "${XSRFToken}")
  .body (ElFileBody ("FPL01SoT2.json") ) )
}

  group("PRL_FL01_530_SoT3") {
  exec (http ("PRL_FL01_530_SoT3")
  .post (baseURL + "/data/cases/1647252224099105/events")
  .headers (CommonHeader.headers_ApplicantsFamiliy)
  .header ("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
  .header ("x-xsrf-token", "${XSRFToken}")
  .body (ElFileBody ("FPL01SoT3.json") ) )
}

  group("PRL_FL01_531_SoT4") {
  exec (http ("PRL_FL01_531_SoT4")
  .post (baseURL + "/workallocation/searchForCompletable")
  .headers (CommonHeader.headers_ApplicantsFamiliy)
  .header ("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
  .header ("x-xsrf-token", "${XSRFToken}")
  .body (ElFileBody ("FPL01SoT4.json") ) )
}

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
