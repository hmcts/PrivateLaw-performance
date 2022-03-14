var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "4",
        "ok": "2",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "375",
        "ok": "412",
        "ko": "375"
    },
    "maxResponseTime": {
        "total": "415",
        "ok": "415",
        "ko": "375"
    },
    "meanResponseTime": {
        "total": "394",
        "ok": "414",
        "ko": "375"
    },
    "standardDeviation": {
        "total": "19",
        "ok": "2",
        "ko": "0"
    },
    "percentiles1": {
        "total": "394",
        "ok": "414",
        "ko": "375"
    },
    "percentiles2": {
        "total": "413",
        "ok": "414",
        "ko": "375"
    },
    "percentiles3": {
        "total": "415",
        "ok": "415",
        "ko": "375"
    },
    "percentiles4": {
        "total": "415",
        "ok": "415",
        "ko": "375"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 2,
    "percentage": 50
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 2,
    "percentage": 50
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.047",
        "ok": "0.023",
        "ko": "0.023"
    }
},
contents: {
"req_010-getauthtoke-4f476": {
        type: "REQUEST",
        name: "010_GetAuthToken",
path: "010_GetAuthToken",
pathFormatted: "req_010-getauthtoke-4f476",
stats: {
    "name": "010_GetAuthToken",
    "numberOfRequests": {
        "total": "2",
        "ok": "0",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "375",
        "ok": "-",
        "ko": "375"
    },
    "maxResponseTime": {
        "total": "375",
        "ok": "-",
        "ko": "375"
    },
    "meanResponseTime": {
        "total": "375",
        "ok": "-",
        "ko": "375"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "-",
        "ko": "0"
    },
    "percentiles1": {
        "total": "375",
        "ok": "-",
        "ko": "375"
    },
    "percentiles2": {
        "total": "375",
        "ok": "-",
        "ko": "375"
    },
    "percentiles3": {
        "total": "375",
        "ok": "-",
        "ko": "375"
    },
    "percentiles4": {
        "total": "375",
        "ok": "-",
        "ko": "375"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 2,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.023",
        "ok": "-",
        "ko": "0.023"
    }
}
    },"req_020-getservicet-47b78": {
        type: "REQUEST",
        name: "020_GetServiceToken",
path: "020_GetServiceToken",
pathFormatted: "req_020-getservicet-47b78",
stats: {
    "name": "020_GetServiceToken",
    "numberOfRequests": {
        "total": "2",
        "ok": "2",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "412",
        "ok": "412",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "415",
        "ok": "415",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "414",
        "ok": "414",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2",
        "ok": "2",
        "ko": "-"
    },
    "percentiles1": {
        "total": "414",
        "ok": "414",
        "ko": "-"
    },
    "percentiles2": {
        "total": "414",
        "ok": "414",
        "ko": "-"
    },
    "percentiles3": {
        "total": "415",
        "ok": "415",
        "ko": "-"
    },
    "percentiles4": {
        "total": "415",
        "ok": "415",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 2,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.023",
        "ok": "0.023",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
