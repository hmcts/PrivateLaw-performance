package uk.gov.hmcts.paybubble.util

object CommonHeader {
 val baseURL = Environment.baseURL
 val IdamUrl = Environment.IdamURL


 //PrivateLaw Headers

 val headers_Cases = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control"-> "max-age=0",
  "referer" -> "https://manage-case.aat.platform.hmcts.net/",
  "cookie"-> "__userid__=b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81; xui-webapp=s%3AE8QEE9f7ZQJRMBsUZTvxJ9mbOjsl3oSP.BURQaXaKpL7b5%2BSmXXS9PxaKdpkP6ilghhf1WD5I7oo; __auth__=eyJ0eXAiOiJKV1QiLCJ6aXAiOiJOT05FIiwia2lkIjoiMWVyMFdSd2dJT1RBRm9qRTRyQy9mYmVLdTNJPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmcHJsX2Nhc2V3b3JrZXJfc29saWNpdG9yQG1haWxpbmF0b3IuY29tIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1dGhfbGV2ZWwiOjAsImF1ZGl0VHJhY2tpbmdJZCI6IjYwNDBmMzljLTE1M2UtNDRiMS04ZTlhLTMxNDNiZDQ4NjVkYS0yMjgyMzE2NiIsImlzcyI6Imh0dHBzOi8vZm9yZ2Vyb2NrLWFtLnNlcnZpY2UuY29yZS1jb21wdXRlLWlkYW0tYWF0Mi5pbnRlcm5hbDo4NDQzL29wZW5hbS9vYXV0aDIvcmVhbG1zL3Jvb3QvcmVhbG1zL2htY3RzIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiZkZKWnptQXhvYlZEcC1TODZLS1ZDa3FBb0xRIiwibm9uY2UiOiIzbXV2R0FHR1JHZnlidUwzVHBaSnpMOXZqdDFobm5GZGowVG5MTGVhTjlvIiwiYXVkIjoieHVpd2ViYXBwIiwibmJmIjoxNjQ3MDEwNzgyLCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInJvbGVzIiwiY3JlYXRlLXVzZXIiLCJtYW5hZ2UtdXNlciIsInNlYXJjaC11c2VyIl0sImF1dGhfdGltZSI6MTY0NzAxMDc4MiwicmVhbG0iOiIvaG1jdHMiLCJleHAiOjE2NDcwMzk1ODIsImlhdCI6MTY0NzAxMDc4MiwiZXhwaXJlc19pbiI6Mjg4MDAsImp0aSI6Ilhvb3dsak9Vd0VLTzFwOWFFVlNmMmNsWU9TSSJ9.dQRjyPqonXY0HUlgdwrf3p4gzrPZEyEt-9nx0zVWqV8cS462lxxy18cvz5fjWy2AjCXl-9sJ_Mh5s0VQ_9iGd-drzafo1x7oYHyMC4UnfeAnoggVxdhRnoN4QLmnviJQeVmvUXcEdNBjes67bt_eOyKfVzZENW-Rhs425wUEh6FlpaFEHhz9uKU1jtTGm0i6DpvEP_0pxI4CU4kHKmzg7s8ECjtXxToSetTpnedpypf0hl8NV-9mDjwZbq53Fv-5BfQ6ImBfqA0X6x-38ppSil8qI2dJ33MwtAtHS8hFhWUReAMnddrwLYnCOswXz_wA91rU6rKx5U0IMfB6zcokYw; dtCookie=v_4_srv_2_sn_1EE0C86FBAB9384A6E2C4CEE45B11B5C_perc_100000_ol_0_mul_1_app-3Aec5df3e8798c1647_1_app-3Ad95979fbf884c21e_1_rcs-3Acss_0; XSRF-TOKEN=BaabkNZL-uyJuHEv571PPybpacRP34yLET0s",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "none",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")


 val headers_CreateCases = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control"-> "max-age=0",
  "content-type"->  "application/json",
  "dtPC"->"1$10804536_342h58vMKHPNOLRTVKTMJPEUFOCWMLSCEPAWDIB-0e0",
  "referer" -> "https://manage-case.aat.platform.hmcts.net/",
  "cookie"-> "__userid__=b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81; xui-webapp=s%3AE8QEE9f7ZQJRMBsUZTvxJ9mbOjsl3oSP.BURQaXaKpL7b5%2BSmXXS9PxaKdpkP6ilghhf1WD5I7oo; __auth__=eyJ0eXAiOiJKV1QiLCJ6aXAiOiJOT05FIiwia2lkIjoiMWVyMFdSd2dJT1RBRm9qRTRyQy9mYmVLdTNJPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmcHJsX2Nhc2V3b3JrZXJfc29saWNpdG9yQG1haWxpbmF0b3IuY29tIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1dGhfbGV2ZWwiOjAsImF1ZGl0VHJhY2tpbmdJZCI6IjYwNDBmMzljLTE1M2UtNDRiMS04ZTlhLTMxNDNiZDQ4NjVkYS0yMjgyMzE2NiIsImlzcyI6Imh0dHBzOi8vZm9yZ2Vyb2NrLWFtLnNlcnZpY2UuY29yZS1jb21wdXRlLWlkYW0tYWF0Mi5pbnRlcm5hbDo4NDQzL29wZW5hbS9vYXV0aDIvcmVhbG1zL3Jvb3QvcmVhbG1zL2htY3RzIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiZkZKWnptQXhvYlZEcC1TODZLS1ZDa3FBb0xRIiwibm9uY2UiOiIzbXV2R0FHR1JHZnlidUwzVHBaSnpMOXZqdDFobm5GZGowVG5MTGVhTjlvIiwiYXVkIjoieHVpd2ViYXBwIiwibmJmIjoxNjQ3MDEwNzgyLCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInJvbGVzIiwiY3JlYXRlLXVzZXIiLCJtYW5hZ2UtdXNlciIsInNlYXJjaC11c2VyIl0sImF1dGhfdGltZSI6MTY0NzAxMDc4MiwicmVhbG0iOiIvaG1jdHMiLCJleHAiOjE2NDcwMzk1ODIsImlhdCI6MTY0NzAxMDc4MiwiZXhwaXJlc19pbiI6Mjg4MDAsImp0aSI6Ilhvb3dsak9Vd0VLTzFwOWFFVlNmMmNsWU9TSSJ9.dQRjyPqonXY0HUlgdwrf3p4gzrPZEyEt-9nx0zVWqV8cS462lxxy18cvz5fjWy2AjCXl-9sJ_Mh5s0VQ_9iGd-drzafo1x7oYHyMC4UnfeAnoggVxdhRnoN4QLmnviJQeVmvUXcEdNBjes67bt_eOyKfVzZENW-Rhs425wUEh6FlpaFEHhz9uKU1jtTGm0i6DpvEP_0pxI4CU4kHKmzg7s8ECjtXxToSetTpnedpypf0hl8NV-9mDjwZbq53Fv-5BfQ6ImBfqA0X6x-38ppSil8qI2dJ33MwtAtHS8hFhWUReAMnddrwLYnCOswXz_wA91rU6rKx5U0IMfB6zcokYw; dtCookie=v_4_srv_2_sn_1EE0C86FBAB9384A6E2C4CEE45B11B5C_perc_100000_ol_0_mul_1_app-3Aec5df3e8798c1647_1_app-3Ad95979fbf884c21e_1_rcs-3Acss_0; XSRF-TOKEN=BaabkNZL-uyJuHEv571PPybpacRP34yLET0s",
  "request-context"-> "appId=cid-v1:46b383c1-eae6-46f8-a657-f5639d68b379",
  "request-id"-> "|m9JlT.XB6gz",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "none",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")

 val headers_TypeofApplication = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control"-> "max-age=0",
  "content-type"->  "application/json",
  "dtPC"->"1$10804536_342h58vMKHPNOLRTVKTMJPEUFOCWMLSCEPAWDIB-0e0",
  "referer" -> "https://manage-case.aat.platform.hmcts.net/",
  "cookie"-> "__userid__=b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81; xui-webapp=s%3AE8QEE9f7ZQJRMBsUZTvxJ9mbOjsl3oSP.BURQaXaKpL7b5%2BSmXXS9PxaKdpkP6ilghhf1WD5I7oo; __auth__=eyJ0eXAiOiJKV1QiLCJ6aXAiOiJOT05FIiwia2lkIjoiMWVyMFdSd2dJT1RBRm9qRTRyQy9mYmVLdTNJPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmcHJsX2Nhc2V3b3JrZXJfc29saWNpdG9yQG1haWxpbmF0b3IuY29tIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1dGhfbGV2ZWwiOjAsImF1ZGl0VHJhY2tpbmdJZCI6IjYwNDBmMzljLTE1M2UtNDRiMS04ZTlhLTMxNDNiZDQ4NjVkYS0yMjgyMzE2NiIsImlzcyI6Imh0dHBzOi8vZm9yZ2Vyb2NrLWFtLnNlcnZpY2UuY29yZS1jb21wdXRlLWlkYW0tYWF0Mi5pbnRlcm5hbDo4NDQzL29wZW5hbS9vYXV0aDIvcmVhbG1zL3Jvb3QvcmVhbG1zL2htY3RzIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiZkZKWnptQXhvYlZEcC1TODZLS1ZDa3FBb0xRIiwibm9uY2UiOiIzbXV2R0FHR1JHZnlidUwzVHBaSnpMOXZqdDFobm5GZGowVG5MTGVhTjlvIiwiYXVkIjoieHVpd2ViYXBwIiwibmJmIjoxNjQ3MDEwNzgyLCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInJvbGVzIiwiY3JlYXRlLXVzZXIiLCJtYW5hZ2UtdXNlciIsInNlYXJjaC11c2VyIl0sImF1dGhfdGltZSI6MTY0NzAxMDc4MiwicmVhbG0iOiIvaG1jdHMiLCJleHAiOjE2NDcwMzk1ODIsImlhdCI6MTY0NzAxMDc4MiwiZXhwaXJlc19pbiI6Mjg4MDAsImp0aSI6Ilhvb3dsak9Vd0VLTzFwOWFFVlNmMmNsWU9TSSJ9.dQRjyPqonXY0HUlgdwrf3p4gzrPZEyEt-9nx0zVWqV8cS462lxxy18cvz5fjWy2AjCXl-9sJ_Mh5s0VQ_9iGd-drzafo1x7oYHyMC4UnfeAnoggVxdhRnoN4QLmnviJQeVmvUXcEdNBjes67bt_eOyKfVzZENW-Rhs425wUEh6FlpaFEHhz9uKU1jtTGm0i6DpvEP_0pxI4CU4kHKmzg7s8ECjtXxToSetTpnedpypf0hl8NV-9mDjwZbq53Fv-5BfQ6ImBfqA0X6x-38ppSil8qI2dJ33MwtAtHS8hFhWUReAMnddrwLYnCOswXz_wA91rU6rKx5U0IMfB6zcokYw; dtCookie=v_4_srv_2_sn_1EE0C86FBAB9384A6E2C4CEE45B11B5C_perc_100000_ol_0_mul_1_app-3Aec5df3e8798c1647_1_app-3Ad95979fbf884c21e_1_rcs-3Acss_0; XSRF-TOKEN=BaabkNZL-uyJuHEv571PPybpacRP34yLET0s",
  "request-context"-> "appId=cid-v1:46b383c1-eae6-46f8-a657-f5639d68b379",
  "request-id"-> "|m9JlT.XB6gz",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "none",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")

 val headers_WithoutNotice = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control"-> "max-age=0",
  "content-type"->  "application/json",
  "dtPC"->"1$10804536_342h58vMKHPNOLRTVKTMJPEUFOCWMLSCEPAWDIB-0e0",
  "referer" -> "https://manage-case.aat.platform.hmcts.net/",
  "cookie"-> "__userid__=b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81; xui-webapp=s%3AE8QEE9f7ZQJRMBsUZTvxJ9mbOjsl3oSP.BURQaXaKpL7b5%2BSmXXS9PxaKdpkP6ilghhf1WD5I7oo; __auth__=eyJ0eXAiOiJKV1QiLCJ6aXAiOiJOT05FIiwia2lkIjoiMWVyMFdSd2dJT1RBRm9qRTRyQy9mYmVLdTNJPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmcHJsX2Nhc2V3b3JrZXJfc29saWNpdG9yQG1haWxpbmF0b3IuY29tIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1dGhfbGV2ZWwiOjAsImF1ZGl0VHJhY2tpbmdJZCI6IjYwNDBmMzljLTE1M2UtNDRiMS04ZTlhLTMxNDNiZDQ4NjVkYS0yMjgyMzE2NiIsImlzcyI6Imh0dHBzOi8vZm9yZ2Vyb2NrLWFtLnNlcnZpY2UuY29yZS1jb21wdXRlLWlkYW0tYWF0Mi5pbnRlcm5hbDo4NDQzL29wZW5hbS9vYXV0aDIvcmVhbG1zL3Jvb3QvcmVhbG1zL2htY3RzIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiZkZKWnptQXhvYlZEcC1TODZLS1ZDa3FBb0xRIiwibm9uY2UiOiIzbXV2R0FHR1JHZnlidUwzVHBaSnpMOXZqdDFobm5GZGowVG5MTGVhTjlvIiwiYXVkIjoieHVpd2ViYXBwIiwibmJmIjoxNjQ3MDEwNzgyLCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInJvbGVzIiwiY3JlYXRlLXVzZXIiLCJtYW5hZ2UtdXNlciIsInNlYXJjaC11c2VyIl0sImF1dGhfdGltZSI6MTY0NzAxMDc4MiwicmVhbG0iOiIvaG1jdHMiLCJleHAiOjE2NDcwMzk1ODIsImlhdCI6MTY0NzAxMDc4MiwiZXhwaXJlc19pbiI6Mjg4MDAsImp0aSI6Ilhvb3dsak9Vd0VLTzFwOWFFVlNmMmNsWU9TSSJ9.dQRjyPqonXY0HUlgdwrf3p4gzrPZEyEt-9nx0zVWqV8cS462lxxy18cvz5fjWy2AjCXl-9sJ_Mh5s0VQ_9iGd-drzafo1x7oYHyMC4UnfeAnoggVxdhRnoN4QLmnviJQeVmvUXcEdNBjes67bt_eOyKfVzZENW-Rhs425wUEh6FlpaFEHhz9uKU1jtTGm0i6DpvEP_0pxI4CU4kHKmzg7s8ECjtXxToSetTpnedpypf0hl8NV-9mDjwZbq53Fv-5BfQ6ImBfqA0X6x-38ppSil8qI2dJ33MwtAtHS8hFhWUReAMnddrwLYnCOswXz_wA91rU6rKx5U0IMfB6zcokYw; dtCookie=v_4_srv_2_sn_1EE0C86FBAB9384A6E2C4CEE45B11B5C_perc_100000_ol_0_mul_1_app-3Aec5df3e8798c1647_1_app-3Ad95979fbf884c21e_1_rcs-3Acss_0; XSRF-TOKEN=BaabkNZL-uyJuHEv571PPybpacRP34yLET0s",
  "request-context"-> "appId=cid-v1:46b383c1-eae6-46f8-a657-f5639d68b379",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "none",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")

 val headers_ApplicantDetails = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control"-> "max-age=0",
  "content-type"->  "application/json",
  "dtPC"->"1$10804536_342h58vMKHPNOLRTVKTMJPEUFOCWMLSCEPAWDIB-0e0",
  "referer" -> "https://manage-case.aat.platform.hmcts.net/",
  "cookie"-> "hmcts-exui-cookies-b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81-mc-accepted=false; __userid__=b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81; xui-webapp=s%3A4xrxcZSMDXRcgHJPaOo4PX3rMTxvDUTr.iKYPLYKcOAC30mZp2DwjgZlJk156vkTpkJ5ON40Z0Ug; __auth__=eyJ0eXAiOiJKV1QiLCJ6aXAiOiJOT05FIiwia2lkIjoiMWVyMFdSd2dJT1RBRm9qRTRyQy9mYmVLdTNJPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmcHJsX2Nhc2V3b3JrZXJfc29saWNpdG9yQG1haWxpbmF0b3IuY29tIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1dGhfbGV2ZWwiOjAsImF1ZGl0VHJhY2tpbmdJZCI6ImY3ZjVmNjM4LTBmMDQtNGQxNC1hZmZlLWFjZWVjNzkyYjBjMy00MDExNjcxMyIsImlzcyI6Imh0dHBzOi8vZm9yZ2Vyb2NrLWFtLnNlcnZpY2UuY29yZS1jb21wdXRlLWlkYW0tYWF0Mi5pbnRlcm5hbDo4NDQzL29wZW5hbS9vYXV0aDIvcmVhbG1zL3Jvb3QvcmVhbG1zL2htY3RzIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiM1pWSW0zYUhjRnlvR3VVMkVBSElwTkFSS1o0Iiwibm9uY2UiOiJuU3FtY1UzZ3A1cmczMElVZlRHMmc1Wlc4aDVOUkNCbzJDSG1oT0E5MjAwIiwiYXVkIjoieHVpd2ViYXBwIiwibmJmIjoxNjQ3MzQ1MDE5LCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInJvbGVzIiwiY3JlYXRlLXVzZXIiLCJtYW5hZ2UtdXNlciIsInNlYXJjaC11c2VyIl0sImF1dGhfdGltZSI6MTY0NzM0NTAxOSwicmVhbG0iOiIvaG1jdHMiLCJleHAiOjE2NDczNzM4MTksImlhdCI6MTY0NzM0NTAxOSwiZXhwaXJlc19pbiI6Mjg4MDAsImp0aSI6Ik5qNmFYVTFTSE94TlUwOC1ZRmUzdjQ5RWRjYyJ9.miaU5oyJg_OYonMDw6UeuPqlF-skmX2_WRyN6F7m1z4tu_uvzKXDMLOZ9NnLe_H9jQiN9AEpPxgx-uMokwVAbu2PrJi2R2Y-7L_ue9WAufGW1Zrebn0GxhCxrko2u6KgBBiuILyVShaul_mvG-PeOm702MNLgd2BRZ8wylIgh7J5PYeUv6IYsDSpi_yOo6QhLVIpC2r2zafhg_Yn34kK9_6sBOpVz-oJqPjzzN7pijEGpsIKUAlK8aI8-b83nepgfPy3hKn4Vwn1jj3PGDUg8spiNx6ha_lRa0cYQGJubgeSz919S44BEsB2dBtFPaCAR-IS4V8nNcFGnsbgPI2vWw; dtCookie=v_4_srv_2_sn_D7E6A7787EA1007922775E373FE1638F_perc_100000_ol_0_mul_1_app-3Aec5df3e8798c1647_1_app-3Ad95979fbf884c21e_1_rcs-3Acss_0; rxVisitor=16468226077511JNF9RF5IG9R2FV0JKO18OC5Q2Q1ICKQ; dtPC=2$362508537_30h-vAFWVCPOUJFUJANKMUFLWRCMHEAKBKMGK-0e0; XSRF-TOKEN=xlH3nv9D-_XY1xdGfWRmGrm4e0-UqvLoE84I; dtLatC=1; rxvt=1647365989939|1647362508541; dtSa=true%7CC%7C-1%7CApplicant%20details%7C-%7C1647364189945%7C362508537_30%7Chttps%3A%2F%2Fmanage-case.aat.platform.hmcts.net%2Fcases%2Fcase-details%2F1647252224099105%7C%7C%7C%7C",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "none",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")

 val headers_RespondentDetails = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control"-> "max-age=0",
  "content-type"->  "application/json",
  "dtPC"->"1$10804536_342h58vMKHPNOLRTVKTMJPEUFOCWMLSCEPAWDIB-0e0",
  "referer" -> "https://manage-case.aat.platform.hmcts.net/",
  "cookie"-> "hmcts-exui-cookies-b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81-mc-accepted=false; __userid__=b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81; xui-webapp=s%3A4xrxcZSMDXRcgHJPaOo4PX3rMTxvDUTr.iKYPLYKcOAC30mZp2DwjgZlJk156vkTpkJ5ON40Z0Ug; __auth__=eyJ0eXAiOiJKV1QiLCJ6aXAiOiJOT05FIiwia2lkIjoiMWVyMFdSd2dJT1RBRm9qRTRyQy9mYmVLdTNJPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmcHJsX2Nhc2V3b3JrZXJfc29saWNpdG9yQG1haWxpbmF0b3IuY29tIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1dGhfbGV2ZWwiOjAsImF1ZGl0VHJhY2tpbmdJZCI6ImY3ZjVmNjM4LTBmMDQtNGQxNC1hZmZlLWFjZWVjNzkyYjBjMy00MDExNjcxMyIsImlzcyI6Imh0dHBzOi8vZm9yZ2Vyb2NrLWFtLnNlcnZpY2UuY29yZS1jb21wdXRlLWlkYW0tYWF0Mi5pbnRlcm5hbDo4NDQzL29wZW5hbS9vYXV0aDIvcmVhbG1zL3Jvb3QvcmVhbG1zL2htY3RzIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiM1pWSW0zYUhjRnlvR3VVMkVBSElwTkFSS1o0Iiwibm9uY2UiOiJuU3FtY1UzZ3A1cmczMElVZlRHMmc1Wlc4aDVOUkNCbzJDSG1oT0E5MjAwIiwiYXVkIjoieHVpd2ViYXBwIiwibmJmIjoxNjQ3MzQ1MDE5LCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInJvbGVzIiwiY3JlYXRlLXVzZXIiLCJtYW5hZ2UtdXNlciIsInNlYXJjaC11c2VyIl0sImF1dGhfdGltZSI6MTY0NzM0NTAxOSwicmVhbG0iOiIvaG1jdHMiLCJleHAiOjE2NDczNzM4MTksImlhdCI6MTY0NzM0NTAxOSwiZXhwaXJlc19pbiI6Mjg4MDAsImp0aSI6Ik5qNmFYVTFTSE94TlUwOC1ZRmUzdjQ5RWRjYyJ9.miaU5oyJg_OYonMDw6UeuPqlF-skmX2_WRyN6F7m1z4tu_uvzKXDMLOZ9NnLe_H9jQiN9AEpPxgx-uMokwVAbu2PrJi2R2Y-7L_ue9WAufGW1Zrebn0GxhCxrko2u6KgBBiuILyVShaul_mvG-PeOm702MNLgd2BRZ8wylIgh7J5PYeUv6IYsDSpi_yOo6QhLVIpC2r2zafhg_Yn34kK9_6sBOpVz-oJqPjzzN7pijEGpsIKUAlK8aI8-b83nepgfPy3hKn4Vwn1jj3PGDUg8spiNx6ha_lRa0cYQGJubgeSz919S44BEsB2dBtFPaCAR-IS4V8nNcFGnsbgPI2vWw; dtCookie=v_4_srv_2_sn_D7E6A7787EA1007922775E373FE1638F_perc_100000_ol_0_mul_1_app-3Aec5df3e8798c1647_1_app-3Ad95979fbf884c21e_1_rcs-3Acss_0; rxVisitor=16468226077511JNF9RF5IG9R2FV0JKO18OC5Q2Q1ICKQ; dtPC=2$362508537_30h-vAFWVCPOUJFUJANKMUFLWRCMHEAKBKMGK-0e0; XSRF-TOKEN=xlH3nv9D-_XY1xdGfWRmGrm4e0-UqvLoE84I; dtLatC=1; rxvt=1647365989939|1647362508541; dtSa=true%7CC%7C-1%7CApplicant%20details%7C-%7C1647364189945%7C362508537_30%7Chttps%3A%2F%2Fmanage-case.aat.platform.hmcts.net%2Fcases%2Fcase-details%2F1647252224099105%7C%7C%7C%7C",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "none",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")

 val headers_ApplicantsFamiliy = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control"-> "max-age=0",
  "content-type"->  "application/json",
  "referer" -> "https://manage-case.aat.platform.hmcts.net/",
  "cookie"-> "hmcts-exui-cookies-b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81-mc-accepted=false; __userid__=b0bab4c6-0ef7-4ffb-89ee-5e7836d55c81; xui-webapp=s%3A4xrxcZSMDXRcgHJPaOo4PX3rMTxvDUTr.iKYPLYKcOAC30mZp2DwjgZlJk156vkTpkJ5ON40Z0Ug; __auth__=eyJ0eXAiOiJKV1QiLCJ6aXAiOiJOT05FIiwia2lkIjoiMWVyMFdSd2dJT1RBRm9qRTRyQy9mYmVLdTNJPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmcHJsX2Nhc2V3b3JrZXJfc29saWNpdG9yQG1haWxpbmF0b3IuY29tIiwiY3RzIjoiT0FVVEgyX1NUQVRFTEVTU19HUkFOVCIsImF1dGhfbGV2ZWwiOjAsImF1ZGl0VHJhY2tpbmdJZCI6ImY3ZjVmNjM4LTBmMDQtNGQxNC1hZmZlLWFjZWVjNzkyYjBjMy00MDExNjcxMyIsImlzcyI6Imh0dHBzOi8vZm9yZ2Vyb2NrLWFtLnNlcnZpY2UuY29yZS1jb21wdXRlLWlkYW0tYWF0Mi5pbnRlcm5hbDo4NDQzL29wZW5hbS9vYXV0aDIvcmVhbG1zL3Jvb3QvcmVhbG1zL2htY3RzIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiM1pWSW0zYUhjRnlvR3VVMkVBSElwTkFSS1o0Iiwibm9uY2UiOiJuU3FtY1UzZ3A1cmczMElVZlRHMmc1Wlc4aDVOUkNCbzJDSG1oT0E5MjAwIiwiYXVkIjoieHVpd2ViYXBwIiwibmJmIjoxNjQ3MzQ1MDE5LCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInJvbGVzIiwiY3JlYXRlLXVzZXIiLCJtYW5hZ2UtdXNlciIsInNlYXJjaC11c2VyIl0sImF1dGhfdGltZSI6MTY0NzM0NTAxOSwicmVhbG0iOiIvaG1jdHMiLCJleHAiOjE2NDczNzM4MTksImlhdCI6MTY0NzM0NTAxOSwiZXhwaXJlc19pbiI6Mjg4MDAsImp0aSI6Ik5qNmFYVTFTSE94TlUwOC1ZRmUzdjQ5RWRjYyJ9.miaU5oyJg_OYonMDw6UeuPqlF-skmX2_WRyN6F7m1z4tu_uvzKXDMLOZ9NnLe_H9jQiN9AEpPxgx-uMokwVAbu2PrJi2R2Y-7L_ue9WAufGW1Zrebn0GxhCxrko2u6KgBBiuILyVShaul_mvG-PeOm702MNLgd2BRZ8wylIgh7J5PYeUv6IYsDSpi_yOo6QhLVIpC2r2zafhg_Yn34kK9_6sBOpVz-oJqPjzzN7pijEGpsIKUAlK8aI8-b83nepgfPy3hKn4Vwn1jj3PGDUg8spiNx6ha_lRa0cYQGJubgeSz919S44BEsB2dBtFPaCAR-IS4V8nNcFGnsbgPI2vWw; dtCookie=v_4_srv_2_sn_D7E6A7787EA1007922775E373FE1638F_perc_100000_ol_0_mul_1_app-3Aec5df3e8798c1647_1_app-3Ad95979fbf884c21e_1_rcs-3Acss_0; rxVisitor=16468226077511JNF9RF5IG9R2FV0JKO18OC5Q2Q1ICKQ; dtPC=2$362508537_30h-vAFWVCPOUJFUJANKMUFLWRCMHEAKBKMGK-0e0; XSRF-TOKEN=xlH3nv9D-_XY1xdGfWRmGrm4e0-UqvLoE84I; dtLatC=1; rxvt=1647365989939|1647362508541; dtSa=true%7CC%7C-1%7CApplicant%20details%7C-%7C1647364189945%7C362508537_30%7Chttps%3A%2F%2Fmanage-case.aat.platform.hmcts.net%2Fcases%2Fcase-details%2F1647252224099105%7C%7C%7C%7C",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "none",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")

}
