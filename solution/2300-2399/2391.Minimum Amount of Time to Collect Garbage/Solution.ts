function garbageCollection(garbage: string[], travel: number[]): number {
    const last: Map<string, number> = new Map();
    let ans = 0;
    for (let i = 0; i < garbage.length; ++i) {
        const s = garbage[i];
        ans += s.length;
        for (const c of s) {
            last.set(c, i);
        }
    }
    let ts = 0;
    for (let i = 1; i <= travel.length; ++i) {
        ts += travel[i - 1];
        for (const [_, j] of last) {
            if (i === j) {
                ans += ts;
            }
        }
    }
    return ans;
}
