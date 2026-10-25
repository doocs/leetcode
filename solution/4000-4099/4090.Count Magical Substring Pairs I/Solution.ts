function countMagicalPairs(s: string): number {
    const n = s.length;
    let ans = 0;
    for (let j = 0; j < n - 1; j++) {
        const cnt1 = new Map<string, number>();
        const cnt = new Array(27).fill(0);
        for (let i = j; i >= 0; i--) {
            const c = s[i];
            cnt1.set(c, (cnt1.get(c) ?? 0) + 1);
            cnt[cnt1.size]++;
        }
        const cnt2 = new Map<string, number>();
        for (let k = j + 1; k < n; k++) {
            const c = s[k];
            cnt2.set(c, (cnt2.get(c) ?? 0) + 1);
            ans += cnt[cnt2.size];
        }
    }
    return ans;
}
