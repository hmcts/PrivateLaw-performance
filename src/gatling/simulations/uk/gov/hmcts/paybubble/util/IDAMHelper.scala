package uk.gov.hmcts.paybubble.util

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.paybubble.util.Environment._

object IDAMHelper {

 /* private val USERNAME = "testytesttest@test.net"
  private val PASSWORD = ""*/
// private val USERNAME = "james@swansea.gov.uk"
//  private val PASSWORD = ""
  private val USERNAME = "ccdloadtest1@gmail.com"
  private val PASSWORD = ""
// private val USERNAME = "emshowcase@hmcts.net"
 // private val PASSWORD = ""
  // below are for aat
/*private val USERNAME = "bundle-tester--518511189@gmail.com"
  private val PASSWORD = ""*/


  val thinktime = Environment.thinkTime

  val getIdamToken =
    exec(http("010_GetAuthToken")
        // .post(idamURL  + "/o/token?client_id=" + OAUTH_CLIENT + "&client_secret=" + IDAM_OAUTH_SECRET + "&grant_type=password&scope=openid profile roles search-user&username=kishanki@gmail.com&password=")
          .post(IdamURL  + "/o/token?client_id=" + OAUTH_CLIENT + "&client_secret=" + IDAM_OAUTH_SECRET + "&grant_type=password&scope=openid profile roles search-user&username=perftestways2pay@mailnesia.com&password=")
          .header("Content-Type", "application/x-www-form-urlencoded")
         .header("Content-Length", "0")
         .check(status is 200)
         .check(jsonPath("$.access_token").saveAs("accessToken")))

  val refundsGetIdamToken =
    exec(http("PaymentAPIToken_010_GetAuthToken")
      .post(IdamURL  + "/o/token?client_id=" + OAUTH_CLIENT + "&client_secret=" + IDAM_OAUTH_SECRET + "&grant_type=password&scope=openid profile roles search-user&username=${email}&password=${password}")
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Content-Length", "0")
      .check(status is 200)
      .check(jsonPath("$.access_token").saveAs("accessTokenRefund")))



}
