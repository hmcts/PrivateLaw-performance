package uk.gov.hmcts.paybubble.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import net.sf.saxon.functions.BaseUri_1
import uk.gov.hmcts.paybubble.scenario.PrivateLawLogin.{baseURL, orgDomain}
import uk.gov.hmcts.paybubble.util.{CommonHeader, CommonHeaderC100, Environment, LoginHeader}

object PrivateLawC100 {

  val IdamUrl = Environment.IdamURL
  val baseURL = Environment.baseURL
  val orgDomain = Environment.baseDomainOrg
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  //====================================================================================
  //CreateCase
  //=====================================================================================


  val Cases =

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
      .check(css("input[name='applicantCaseName']", "value").saveAs("CaseName"))
      .check(substring("${CaseName}"))).exitHereIfFailed
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
