function minSteps(s: string, t: string): number {
    let cnt = new Array(128).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0)];
    }
    for (const c of t) {
        --cnt[c.charCodeAt(0)];
    }
    let ans = 0;
    for (const v of cnt) {
        ans += Math.abs(v);
    }
    return ans;
}
