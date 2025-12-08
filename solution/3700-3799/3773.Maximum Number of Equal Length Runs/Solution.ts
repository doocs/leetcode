function maxSameLengthRuns(s: string): number {
    const cnt: Record<number, number> = {};
    const n = s.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            ++j;
        }
        const m = j - i;
        cnt[m] = (cnt[m] || 0) + 1;
        ans = Math.max(ans, cnt[m]);
        i = j;
    }
    return ans;
}
