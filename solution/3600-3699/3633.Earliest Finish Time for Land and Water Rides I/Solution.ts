function earliestFinishTime(
    landStartTime: number[],
    landDuration: number[],
    waterStartTime: number[],
    waterDuration: number[],
): number {
    const x = calc(landStartTime, landDuration, waterStartTime, waterDuration);
    const y = calc(waterStartTime, waterDuration, landStartTime, landDuration);
    return Math.min(x, y);
}

function calc(a1: number[], t1: number[], a2: number[], t2: number[]): number {
    let minEnd = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < a1.length; i++) {
        minEnd = Math.min(minEnd, a1[i] + t1[i]);
    }
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < a2.length; i++) {
        ans = Math.min(ans, Math.max(minEnd, a2[i]) + t2[i]);
    }
    return ans;
}
