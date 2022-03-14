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
        "total": "32",
        "ok": "813",
        "ko": "32"
    },
    "maxResponseTime": {
        "total": "842",
        "ok": "842",
        "ko": "32"
    },
    "meanResponseTime": {
        "total": "430",
        "ok": "828",
        "ko": "32"
    },
    "standardDeviation": {
        "total": "398",
        "ok": "15",
        "ko": "0"
    },
    "percentiles1": {
        "total": "423",
        "ok": "828",
        "ko": "32"
    },
    "percentiles2": {
        "total": "820",
        "ok": "835",
        "ko": "32"
    },
    "percentiles3": {
        "total": "838",
        "ok": "841",
        "ko": "32"
    },
    "percentiles4": {
        "total": "841",
        "ok": "842",
        "ko": "32"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 2,
    "percentage": 50
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
        "ok": "2",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "813",
        "ok": "813",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "842",
        "ok": "842",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "828",
        "ok": "828",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "15",
        "ok": "15",
        "ko": "-"
    },
    "percentiles1": {
        "total": "828",
        "ok": "828",
        "ko": "-"
    },
    "percentiles2": {
        "total": "835",
        "ok": "835",
        "ko": "-"
    },
    "percentiles3": {
        "total": "841",
        "ok": "841",
        "ko": "-"
    },
    "percentiles4": {
        "total": "842",
        "ok": "842",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 2,
    "percentage": 100
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
    },"req_020-getservicet-47b78": {
        type: "REQUEST",
        name: "020_GetServiceToken",
path: "020_GetServiceToken",
pathFormatted: "req_020-getservicet-47b78",
stats: {
    "name": "020_GetServiceToken",
    "numberOfRequests": {
        "total": "2",
        "ok": "0",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "32",
        "ok": "-",
        "ko": "32"
    },
    "maxResponseTime": {
        "total": "32",
        "ok": "-",
        "ko": "32"
    },
    "meanResponseTime": {
        "total": "32",
        "ok": "-",
        "ko": "32"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "-",
        "ko": "0"
    },
    "percentiles1": {
        "total": "32",
        "ok": "-",
        "ko": "32"
    },
    "percentiles2": {
        "total": "32",
        "ok": "-",
        "ko": "32"
    },
    "percentiles3": {
        "total": "32",
        "ok": "-",
        "ko": "32"
    },
    "percentiles4": {
        "total": "32",
        "ok": "-",
        "ko": "32"
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
