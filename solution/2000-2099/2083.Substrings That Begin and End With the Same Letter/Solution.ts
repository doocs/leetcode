function numberOfSubstrings(s: string): number {
    const cnt: Record<string, number> = {};
    let ans = 0;
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
        ans += cnt[c];
    }
    return ans;
}
