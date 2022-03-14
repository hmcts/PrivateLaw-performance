package uk.gov.hmcts.paybubble.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.scenario._
import uk.gov.hmcts.paybubble.scenario._
import uk.gov.hmcts.paybubble.util._
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import scala.util.Random

class
CCPaybubbleSCN extends Simulation {

	val idamUrl = Environment.IdamURL
	val baseURL=Environment.baseURL
	val bulkScanUrl=Environment.bulkScanURL
	val paymentAPIURL=Environment.paymentAPIURL


	val caseNumber = Iterator.continually(Map("case_number" -> (1000000000L * (Random.nextInt(9000000) + 1000000) + Random.nextInt(1000000000))))
	val UUID = Iterator.continually(Map("UUID" -> java.util.UUID.randomUUID.toString))

	val rampUpDurationMins = 2
	val rampDownDurationMins = 2
	val testDurationMins = 60

	val CCDViewPaymentHourlyTarget:Double = 459
	val CCDViewPaymentRatePerSec = CCDViewPaymentHourlyTarget / 3600

	val onlinePaymentHourlyTarget:Double = 220
	val onlinePaymentRatePerSec = onlinePaymentHourlyTarget / 3600

	val bulkscanHourlyTarget:Double = 79
	val bulkscanRatePerSec = bulkscanHourlyTarget / 3600

	val PBAHourlyTarget:Double = 186
	val PBARatePerSec = PBAHourlyTarget / 3600

	val telephonyHourlyTarget:Double = 14
	val telephonyRatePerSec = telephonyHourlyTarget / 3600

	val addOrderHourlyTarget:Double = 531
	val addOrderRatePerSec = addOrderHourlyTarget / 3600

	val createPaymentHourlyTarget:Double = 238
	val createPaymentRatePerSec = createPaymentHourlyTarget / 3600

	val getOrderHourlyTarget:Double = 65
	val getOrderRatePerSec = getOrderHourlyTarget / 3600

	val httpProtocol = http
		.baseUrl(paymentAPIURL)
		//.proxy(Proxy("proxyout.reform.hmcts.net", 8080))

	val bulkscanhttpProtocol = http
		.baseUrl(bulkScanUrl)
		//.proxy(Proxy("proxyout.reform.hmcts.net", 8080))

	val baseProtocol = http
		.baseUrl(baseURL)
		.inferHtmlResources()
		.silentResources


	val PRLFL01 = scenario("PrivateLawFL01").repeat(1)
		{
			exec(PrivateLawLogin.homePage)
			.exec(PrivateLawLogin.PRLLogin)
		}

//PrivateLaw - Test
	setUp(
		PRLFL01.inject(rampUsers(1) during (1 minutes)).protocols(httpProtocol)
	).maxDuration(1 minutes)

	/*setUp(
		Ways2PayCC_Scn.inject(rampUsers(1) during (1 minutes)).protocols(httpProtocol),
		Ways2PayPBA_Scn.inject(rampUsers(1) during (1 minutes)).protocols(httpProtocol)
	).maxDuration(60 minutes)
*/
}
