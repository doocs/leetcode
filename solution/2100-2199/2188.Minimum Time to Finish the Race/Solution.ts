function minimumFinishTime(tires: number[][], changeTime: number, numLaps: number): number {
    const inf = 1 << 30;
    const cost: number[] = Array(18).fill(inf);
    for (const [f, r] of tires) {
        let s = 0;
        let t = f;
        for (let i = 1; t <= changeTime + f; ++i) {
            s += t;
            cost[i] = Math.min(cost[i], s);
            t *= r;
        }
    }
    const f: number[] = Array(numLaps + 1).fill(inf);
    f[0] = -changeTime;
    for (let i = 1; i <= numLaps; ++i) {
        for (let j = 1; j < Math.min(18, i + 1); ++j) {
            f[i] = Math.min(f[i], f[i - j] + cost[j]);
        }
        f[i] += changeTime;
    }
    return f[numLaps];
}
