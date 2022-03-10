function numberOfRounds(startTime: string, finishTime: string): number {
    let m1 = toMinutes(startTime),
        m2 = toMinutes(finishTime);
    if (m1 > m2) {
        m2 += 24 * 60;
    }
    let ans = Math.floor(m2 / 15) - Math.ceil(m1 / 15);
    return ans > 0 ? ans : 0;
}

function toMinutes(time: string): number {
    let [h, m] = time.split(':').map(Number);
    return h * 60 + m;
}
