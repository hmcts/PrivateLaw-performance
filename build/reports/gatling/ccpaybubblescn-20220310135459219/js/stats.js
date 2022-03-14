var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
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
        "total": "281",
        "ok": "-",
        "ko": "281"
    },
    "meanResponseTime": {
        "total": "157",
        "ok": "-",
        "ko": "157"
    },
    "standardDeviation": {
        "total": "125",
        "ok": "-",
        "ko": "125"
    },
    "percentiles1": {
        "total": "157",
        "ok": "-",
        "ko": "157"
    },
    "percentiles2": {
        "total": "219",
        "ok": "-",
        "ko": "219"
    },
    "percentiles3": {
        "total": "269",
        "ok": "-",
        "ko": "269"
    },
    "percentiles4": {
        "total": "279",
        "ok": "-",
        "ko": "279"
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
        "total": "2",
        "ok": "-",
        "ko": "2"
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
        "total": "1",
        "ok": "0",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "281",
        "ok": "-",
        "ko": "281"
    },
    "maxResponseTime": {
        "total": "281",
        "ok": "-",
        "ko": "281"
    },
    "meanResponseTime": {
        "total": "281",
        "ok": "-",
        "ko": "281"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "-",
        "ko": "0"
    },
    "percentiles1": {
        "total": "281",
        "ok": "-",
        "ko": "281"
    },
    "percentiles2": {
        "total": "281",
        "ok": "-",
        "ko": "281"
    },
    "percentiles3": {
        "total": "281",
        "ok": "-",
        "ko": "281"
    },
    "percentiles4": {
        "total": "281",
        "ok": "-",
        "ko": "281"
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
    "count": 1,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1",
        "ok": "-",
        "ko": "1"
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
        "total": "1",
        "ok": "0",
        "ko": "1"
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
    "count": 1,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1",
        "ok": "-",
        "ko": "1"
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
