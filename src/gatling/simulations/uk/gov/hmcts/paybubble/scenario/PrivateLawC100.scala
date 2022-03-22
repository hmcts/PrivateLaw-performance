package uk.gov.hmcts.paybubble.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PrivateLawC100 {

  val IdamUrl = Environment.IdamURL
  val baseURL = Environment.baseURL
  val orgDomain = Environment.baseDomainOrg
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  //====================================================================================
  //CreateCase
  //=====================================================================================


  //====================================================================================
  //CreateCase
  //=====================================================================================

  //add_C001_Title
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


  group("PRL_C100_010_CreateCase") {
    exec(http("PRL_C100_010_CreateCase")
      .get(baseURL + "aggregated/caseworkers/:uid/jurisdictions?access=create")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_020_CreateCase") {
    exec(http("PRL_C100_020_CreateCase")
      .get(baseURL + "/data/internal/case-types/PRLAPPS/event-triggers/solicitorCreate?ignore-warning=false")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

    .group("PRL_C100_030_StartCreateCaseFL01") {
      exec(http("PRL_C100_030_StartCreateCaseFPL01")
        .post(baseURL + "data/case-types/PRLAPPS/validate?pageId=solicitorCreate2")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100StartCreateCase.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
    .pause(MinThinkTime, MaxThinkTime)

    .group("PRL_C100_040_Confidentiality") {
      exec(http("PRL_C100_040_Confidentiality")
        .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=solicitorCreate6")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100Confidentiality.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
    .pause(MinThinkTime, MaxThinkTime)

    .group("PRL_C100_050_CaseName") {
      exec(http("PRL_C100_050_CaseName")
        .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=solicitorCreate4")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100Confidentiality.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
    .pause(MinThinkTime, MaxThinkTime)

    .group("PRL_C100_060_Create") {
      exec(http("PRL_C100_060_Create")
        .post(baseURL + "/data/case-types/PRLAPPS/cases?ignore-warning=false")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100Create.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
    .pause(MinThinkTime, MaxThinkTime)

    .group("PRL_C100_070_Create") {
      exec(http("PRL_C100_070_Create")
        .post(baseURL + "/data/case-types/PRLAPPS/cases?ignore-warning=false")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100Create.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
    .pause(MinThinkTime, MaxThinkTime)

    .group("PRL_C100_080_Create") {
      exec(http("PRL_C100_080_Create")
        .post(baseURL + "/workallocation/searchForCompletable")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100Create1.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
    .pause(MinThinkTime, MaxThinkTime)


  /////Type of Application
  val TypeOfApplication =

    group("PRL_C100_090_TypeOfApplication1") {
      exec(http("PRL_C100_090_TypeOfApplication1")
        .get(baseURL + "/cases/case-details/1647686455589468/trigger/selectApplicationType/selectApplicationType1")
        .headers(CommonHeader.headers_CreateCases)
        .check(status.in(200, 304))).exitHereIfFailed
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }

  group("PRL_C100_100_TypeOfApplication2") {
    exec(http("PRL_C100_100_TypeOfApplication2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=selectApplicationType1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100TypeOfApplication.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  //Document upload
  .group("PRL_C100_110_TypeOfApplication3") {
    exec(http("PRL_C100_110_TypeOfApplication3")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=selectApplicationType2")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100TypeOfApplication1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
  .pause(MinThinkTime, MaxThinkTime)

  .group("PRL_C100_120_TypeOfApplication4") {
    exec(http("PRL_C100_120_TypeOfApplication4")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=selectApplicationType3")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100TypeOfApplication2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
  .pause(MinThinkTime, MaxThinkTime)

  .group("PRL_C100_130_TypeOfApplication5") {
    exec(http("PRL_C100_130_TypeOfApplication5")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=selectApplicationType4")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100TypeOfApplication3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

.pause(MinThinkTime, MaxThinkTime)

  .group("PRL_C100_140_TypeOfApplication6") {
    exec(http("PRL_C100_140_TypeOfApplication6")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100TypeOfApplication4.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  .group("PRL_C100_150_TypeOfApplication7") {
    exec(http("PRL_C100_150_TypeOfApplication7")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100TypeOfApplication5.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
  .pause(MinThinkTime, MaxThinkTime)


/////HearingUrgency
val HearingUrgency =
  group("PRL_C100_160_HearingUrgency1") {
    exec(http("PRL_C100_160_HearingUrgency1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/hearingUrgency/hearingUrgency1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_170_HearingUrgency2") {
    exec(http("PRL_C100_170_HearingUrgency2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=hearingUrgency1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100HearingUrgency1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed

  group("PRL_C100_180_HearingUrgency3") {
    exec(http("PRL_C100_180_HearingUrgency3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100HearingUrgency2.json"))
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_190_HearingUrgency3") {
    exec(http("PRL_C100_190_HearingUrgency3")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100HearingUrgency3.json"))
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)


/////Applicant Details
val ApplicantDetails =
  group("PRL_C100_200_ApplicantDetails1") {
    exec(http("PRL_C100_200_ApplicantDetails1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/applicantsDetails/applicantsDetails1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_210_ApplicantDetails2") {
    exec(http("PRL_C100_210_ApplicantDetails2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=applicantsDetails1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100ApplicantDetails1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed

  group("PRL_C100_220_ApplicantDetails3") {
    exec(http("PRL_C100_220_ApplicantDetails3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100ApplicantDetails2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_230_ApplicantDetails4") {
    exec(http("PRL_C100_230_ApplicantDetails4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100ApplicantDetails3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed

/////Child Details
val ChildDetails =
  group("PRL_C100_240_ChildDetails1") {
    exec(http("PRL_C100_240_ChildDetails1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/childDetails/childDetails1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_250_ChildDetails2") {
    exec(http("PRL_C100_250_ChildDetails2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=childDetails1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100ChildDetails1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_260_ChildDetails3") {
    exec(http("PRL_C100_260_ChildDetails3")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=childDetails2")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100ChildDetails2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed


  group("PRL_C100_270_ChildDetails4") {
    exec(http("PRL_C100_270_ChildDetails4")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100ChildDetails3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed

  group("PRL_C100_280_ChildDetails5") {
    exec(http("PRL_C100_280_ChildDetails5")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100ChildDetails4.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed



/////Respondent Details
val RespondentDetails =
  group("PRL_C100_290_RespodentDetails1") {
    exec(http("PRL_C100_290_RespodentDetails1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/respondentsDetails/respondentsDetails1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

    group("PRL_C100_300_RespodentDetails2") {
      exec(http("PRL_C100_300_RespodentDetails2")
        .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=respondentsDetails1")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100RespondentDetails1.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
      .pause(MinThinkTime, MaxThinkTime)

    group("PRL_C100_310_RespodentDetails3") {
      exec(http("PRL_C100_310_RespodentDetails3")
        .post(baseURL + "/data/cases/1647686455589468/events")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100RespondentDetails2.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
      .pause(MinThinkTime, MaxThinkTime)

    group("PRL_C100_320_RespodentDetails4") {
      exec(http("PRL_C100_320_RespodentDetails4")
        .post(baseURL + "/workallocation/searchForCompletable")
        .headers(CommonHeader.headers_CreateCases)
        .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100RespondentDetails3.json")))
        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
    }
      .pause(MinThinkTime, MaxThinkTime)

/////MIAM
val MIAM =
  group("PRL_C100_330_MIAM1") {
    exec(http("PRL_C100_330_MIAM1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/miam/miam1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_340_MIAM2") {
    exec(http("PRL_C100_340_MIAM2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=miam1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100MIAM1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_350_MIAM3") {
    exec(http("PRL_C100_350_MIAM3")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=miam7")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100MIAM2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_360_MIAM4") {
    exec(http("PRL_C100_360_MIAM4")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100MIAM3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_370_MIAM5") {
    exec(http("PRL_C100_370_MIAM5")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100MIAM4.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  /////AllegationofHarm
val AllegationsOfHarm =
  group("PRL_C100_380_AoH1") {
    exec(http("PRL_C100_380_AoH1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/allegationsOfHarm/allegationsOfHarm1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_390_AoH2") {
    exec(http("PRL_C100_390_AoH2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=allegationsOfHarm1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100AoH1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_400_AoH3") {
    exec(http("PRL_C100_400_AoH3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100AoH2.json")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_410_AoH4") {
    exec(http("PRL_C100_410_AoH4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100AoH3.json")))
  }
    .pause(MinThinkTime, MaxThinkTime)


/////OtherPeopleintheCase
val OtherPeopleintheCase =
  group("PRL_C100_420_OPC1") {
    exec(http("PRL_C100_420_OPC1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/otherPeopleInTheCase/otherPeopleInTheCase1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_430_OPC2") {
    exec(http("PRL_C100_430_OPC2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=otherPeopleInTheCase1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100OPC1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_440_OPC3") {
    exec(http("PRL_C100_440_OPC3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100OPC2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_450_OPC4") {
    exec(http("PRL_C100_450_OPC4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100OPC3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

/////OtherProceedings
val OtherProceedings =
  group("PRL_C100_460_OP1") {
    exec(http("PRL_C100_460_OP1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/otherProceedings/otherProceedings1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_470_OP2") {
    exec(http("PRL_C100_470_OP2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=otherProceedings1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100OP1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_480_OP3") {
    exec(http("PRL_C100_480_OP3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100OP2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_490_OP4") {
    exec(http("PRL_C100_490_OP4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100OP3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

/////AttendingTheHearing
val AttendingHearing =
  group("PRL_C100_500_AH1") {
    exec(http("PRL_C100_500_AH1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/attendingTheHearing/attendingTheHearing1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_510_AH2") {
    exec(http("PRL_C100_510_AH2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=attendingTheHearing1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100AH1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_520_AH3") {
    exec(http("PRL_C100_520_AH3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100AH2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_530_AH4") {
    exec(http("PRL_C100_530_AH4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100AH3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

/////InternationalElement
val InternationalElement =
  group("PRL_C100_540_IE1") {
    exec(http("PRL_C100_540_IE1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/internationalElement/internationalElement1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_540_IE2") {
    exec(http("PRL_C100_540_IE2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=internationalElement1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100IE1.json")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_550_IE3") {
    exec(http("PRL_C100_550_IE3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100IE2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_560_AH4") {
    exec(http("PRL_C100_570_AH4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100IE3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

/////LitigationCapacity
val LitigationCapacity =
  group("PRL_C100_570_LC1") {
    exec(http("PRL_C100_570_LC1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/litigationCapacity/litigationCapacity1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_580_LC2") {
    exec(http("PRL_C100_580_LC2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=litigationCapacity1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100LC1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_590_LC3") {
    exec(http("PRL_C100_590_LC3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100LC2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_600_LC4") {
    exec(http("PRL_C100_600_LC4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100LC3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed

/////WelshLanguage
val WelshLanguage =
  group("PRL_C100_610_Welsh1") {
    exec(http("PRL_C100_610_Welsh1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/welshLanguageRequirements/welshLanguageRequirements1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_620_Welsh2") {
    exec(http("PRL_C100_620_Welsh2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=welshLanguageRequirements1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100WL1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_630_WL3") {
    exec(http("PRL_C100_630_WL3")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100WL2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

  group("PRL_C100_640_WL4") {
    exec(http("PRL_C100_640_WL4")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100WL3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)

/////SubmitandPay
val SubmitandPay =
  group("PRL_C100_650_Submit1") {
    exec(http("PRL_C100_650_Submit1")
      .get(baseURL + "/cases/case-details/1647686455589468/trigger/submitAndPay/submitAndPay1")
      .headers(CommonHeader.headers_CreateCases)
      .check(status.in(200, 304))).exitHereIfFailed
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }

  group("PRL_C100_660_Submit2") {
    exec(http("PRL_C100_660_Submit2")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=submitAndPay1")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100Submit1.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)


  group("PRL_C100_670_Submit3") {
    exec(http("PRL_C100_670_Submit3")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=submitAndPay2")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100Submit2.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)


  group("PRL_C100_680_Submit4") {
    exec(http("PRL_C100_680_Submit4")
      .post(baseURL + "/data/case-types/PRLAPPS/validate?pageId=submitAndPay3")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100Submit3.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed

  group("PRL_C100_690_Submit5") {
    exec(http("PRL_C100_690_Submit5")
      .post(baseURL + "/data/cases/1647686455589468/events")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100Submit4.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)
    .check(status.in(200, 304))).exitHereIfFailed

  group("PRL_C100_700_Submit6") {
    exec(http("PRL_C100_700_Submit6")
      .post(baseURL + "/workallocation/searchForCompletable")
      .headers(CommonHeader.headers_CreateCases)
      .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
      .header("x-xsrf-token", "${XSRFToken}")
      .body(ElFileBody("C100Submit5.json")))
      .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
  }
    .pause(MinThinkTime, MaxThinkTime)


                                  /*  group("PRL_C100_010_Cases") {
                                      exec(http("PRL Case View")
                                        .get(baseURL + "cases")
                                        .headers(CommonHeader.headers_Cases)
                                        .check(status.in(200, 304))).exitHereIfFailed

                                        .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

                                    }

                                      .pause(MinThinkTime, MaxThinkTime)

                                   */

    group("PRL_C100_010_Case_Filter") {

      exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
        .exec(getCookieValue(CookieKey("__auth__").withDomain("manage-case.aat.platform.hmcts.net").saveAs("authTokenResp")))

        .exec(http("PRL Case Filter")
        .get(baseURL + "/aggregated/caseworkers/:uid/jurisdictions?access=create")
        .headers(CommonHeaderC100.get_Header)
        .body(ElFileBody("C100CreateCaseJuristinction.json"))
        .check(substring("Event")))


    }
      .pause(MinThinkTime, MaxThinkTime)







    /*
    group("PRL_C100_010_Case_Filter") {
      exec(http("PRL Case Filter")
        .get(baseURL + "/cases/case-filter")
        .headers(CommonHeaderC100.get_Header)
        .body(ElFileBody("C100CreateCaseJuristinction.json"))
        .check(substring("Event"))).exitHereIfFailed

    }

      .pause(MinThinkTime, MaxThinkTime)

     */


  //==================================================================================
  //Business process : Click Create Case
  //==================================================================================
  val CreateCases =

  group("PRL_C100_020_Create_Case") {
    exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
      .exec(getCookieValue(CookieKey("__auth__").withDomain("manage-case.aat.platform.hmcts.net").saveAs("authTokenResp")))

    .exec(http("PRL_010_005_PRL_010_CreateCase")
     // .get(baseURL + "/cases/case-create/PRIVATELAW/C100/solicitorCreate/solicitorCreate1")
      .get(baseURL + "/data/internal/case-types/C100/event-triggers/solicitorCreate?ignore-warning=false")
      .headers(CommonHeaderC100.case_Type_Get_Header)
      .check(jsonPath("$.event_token").saveAs("event_token"))
      .check(substring("Solicitor application")))

    //  .exec(getCookieValue(CookieKey("event_token").withDomain(orgDomain).saveAs("event_token")))



     // .exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))

    /*  .exec(http("PRL_C100_030_CreateCaseUserDetails")
        .get(baseURL + "/api/user/details")
        .headers(CommonHeaderC100.details_Header)
        .check(status.in(200, 304)))


     */
  }

    .pause(MinThinkTime, MaxThinkTime)

  //==================================================================================
  //Business process : Start Create Case
  //==================================================================================

  .group("PRL_C100_040_Solicitor_Application") {
    exec(getCookieValue(CookieKey("XSRF-TOKEN").withDomain(orgDomain).saveAs("XSRFToken")))
      .exec(getCookieValue(CookieKey("__auth__").withDomain("manage-case.aat.platform.hmcts.net").saveAs("authTokenResp")))

      .exec(http("C100_StartCreateCase")
      .post(baseURL + "/data/case-types/C100/validate?pageId=solicitorCreate1")
      .headers(CommonHeaderC100.post_Header)
     // .header("event_token", "${authTokenResp}")
      .body(ElFileBody("C100ApplicantCaseName.json"))
      .check(substring("applicantCaseName")))
    //  .check(css("input[name='applicantCaseName']", "value").saveAs("CaseName"))
     // .check(substring("${CaseName}"))).exitHereIfFailed
  }

  .group("PRL_C100_050_Solicitor_Check") {
        exec(http("PRL_010_StartCreateCaseFPL01")
          .post("/data/case-types/C100/cases?ignore-warning=false")
          .headers(CommonHeaderC100.post_Case_Header)
          .body(ElFileBody("C100CreateCase.json")))
      }
      .pause(MinThinkTime, MaxThinkTime)


  //==================================================================================
  //Business process : Type of Application
  //==================================================================================

  val TypeOfApplication =

    group("PRL_C100_060_Application_Type") {
      exec(http("PRL_010_Confidentiality Statement")
        .post("/data/internal/cases/1647255857298282/event-triggers/selectApplicationType?ignore-warning=false")
        .headers(CommonHeaderC100.get_Header)
        .header("request-id", "|//cBH.qJxGT")
        .header("x-xsrf-token", "${XSRFToken}")
        .body(ElFileBody("C100TypeOfApplication.json")))
    }
      .pause(MinThinkTime, MaxThinkTime)












      //==================================================================================
      //Business process : SolifictorApplication CaseName
      //==================================================================================

    .group("PRL_C100_070_Document_Upload") {
        exec(http("PRL_010_Confidentiality Statement")
          .post("/document")
          .headers(CommonHeaderC100.Upload_Post_Header)
          .header("accept", "application/vnd.uk.gov.hmcts.ccd-data-store-api.case-data-validate.v2+json;charset=UTF-8")
          .header("request-id", "|gauJa.SLTXX")
          .header("x-xsrf-token", "${XSRFToken}"))

      }
      .pause(MinThinkTime, MaxThinkTime)

      .group("PRL_C100_080_Document_Upload_POST") {
        exec(http("PRL_010_Confidentiality Statement")
          .post("/data/case-types/C100/validate?pageId=selectApplicationType2")
          .headers(CommonHeaderC100.post_Header)
          .header("accept-encoding", "gzip, deflate, br")
          .header("request-id", "gauJa.IvCJG")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("C100upload.json")))

      }
      .pause(MinThinkTime, MaxThinkTime)

      .group("PRL_C100_090_for_permission") {
        exec(http("PRL_010_Confidentiality Statement")
          .post("/data/case-types/C100/validate?pageId=selectApplicationType3")
          .headers(CommonHeaderC100.post_Header)
          .header("accept-encoding", "gzip, deflate, br")
          .header("request-id", "|gauJa.VNfxO")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("C100permissionRequired.json")))

      }
      .pause(MinThinkTime, MaxThinkTime)


      .group("PRL_C100_100_why_application?") {
        exec(http("PRL_010_Confidentiality Statement")
          .post("/data/case-types/C100/validate?pageId=selectApplicationType4")
          .headers(CommonHeaderC100.post_Header)
          .header("accept-encoding", "gzip, deflate, br")
          .header("request-id", "gauJa.vIb/u")
          .header("x-xsrf-token", "${XSRFToken}")
          .body(ElFileBody("C100permissionRequired.json")))

      }
      .pause(MinThinkTime, MaxThinkTime)


  .group("PRL_C100_110_Review_type") {
    exec(http("PRL_010_Confidentiality Statement")
    .post("/data/cases/1647255857298282/events")
    .headers(CommonHeaderC100.post_Header)
    .header("accept-encoding", "gzip, deflate, br")
    .header("request-id", "|gauJa.+zyX")
    .header("x-xsrf-token", "${XSRFToken}")
    .body(ElFileBody("C100TypeOfApplicationDetails.json")))

}
.pause(MinThinkTime, MaxThinkTime)

    //==================================================================================
    //Business process : Log out of Paybubble
    //==================================================================================

  /*  val logout =
      group("PaymentAPI${service}_${SignoutNumber}_Logout") {
        exec(http("PaymentAPI${service}_${SignoutNumber}_010_Logout")
          .get("/logout")
          .headers(CommonHeader.headers_logout)
          .check(regex("This page cannot be found"))
          .check(status.is(404)))
      }


   */
}
