function garbageCollection(garbage: string[], travel: number[]): number {
    const n = garbage.length;
    const m = travel.length;
    let ans = 0;
    const last = new Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        ans += garbage[i].length;
        for (const c of garbage[i]) {
            last[c.charCodeAt(0) - 'A'.charCodeAt(0)] = i;
        }
    }
    const s = new Array(m + 1).fill(0);
    for (let i = 1; i <= m; ++i) {
        s[i] = s[i - 1] + travel[i - 1];
    }
    for (const i of last) {
        ans += s[i];
    }
    return ans;
}
