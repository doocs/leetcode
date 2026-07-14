function secondsBetweenTimes(startTime: string, endTime: string): number {
    return f(endTime) - f(startTime);
}

function f(s: string): number {
    return parseInt(s.slice(0, 2)) * 3600 + parseInt(s.slice(3, 5)) * 60 + parseInt(s.slice(6));
}
