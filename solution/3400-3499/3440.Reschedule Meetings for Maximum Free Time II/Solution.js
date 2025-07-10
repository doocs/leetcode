var maxFreeTime = function(eventTime, startTime, endTime) {
    let n = startTime.length;
    let maxGapBefore = 0, maxFreeTime = 0, lastEnd = 0;

    for (let i = 0; i < n; i++) {
        let duration = endTime[i] - startTime[i];
        let nextStart = (i === n - 1) ? eventTime : startTime[i + 1];
        let freeTime = nextStart - lastEnd;
        if (duration > maxGapBefore) freeTime -= duration;
        maxFreeTime = Math.max(maxFreeTime, freeTime);
        maxGapBefore = Math.max(maxGapBefore, startTime[i] - lastEnd);
        lastEnd = endTime[i];
    }

    let maxGapAfter = 0, lastStart = eventTime;
    for (let i = n - 1; i >= 0; i--) {
        let duration = endTime[i] - startTime[i];
        let prevEnd = (i === 0) ? 0 : endTime[i - 1];
        let freeTime = lastStart - prevEnd;
        if (duration <= maxGapAfter)
            maxFreeTime = Math.max(maxFreeTime, freeTime);
        maxGapAfter = Math.max(maxGapAfter, lastStart - endTime[i]);
        lastStart = startTime[i];
    }

    return maxFreeTime;
};