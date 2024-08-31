function minSteps(s: string, t: string): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let ans = 0;
    for (const c of t) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
            ++ans;
        }
    }
    return ans;
}
