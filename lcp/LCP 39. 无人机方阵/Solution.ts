function minimumSwitchingTimes(source: number[][], target: number[][]): number {
    const cnt: Map<number, number> = new Map();
    for (const row of source) {
        for (const x of row) {
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    for (const row of target) {
        for (const x of row) {
            cnt.set(x, (cnt.get(x) || 0) - 1);
        }
    }
    let ans = 0;
    for (const [_, v] of cnt) {
        ans += Math.abs(v);
    }
    return Math.floor(ans / 2);
}
