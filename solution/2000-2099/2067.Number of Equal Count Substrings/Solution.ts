function equalCountSubstrings(s: string, count: number): number {
    const n = s.length;
    let ans = 0;
    for (let i = 1; i < 27 && i * count <= n; ++i) {
        const k = i * count;
        const cnt: number[] = Array(26).fill(0);
        let t = 0;
        for (let j = 0; j < n; ++j) {
            const a = s.charCodeAt(j) - 97;
            t += ++cnt[a] === count ? 1 : 0;
            t -= cnt[a] === count + 1 ? 1 : 0;
            if (j >= k) {
                const b = s.charCodeAt(j - k) - 97;
                t += --cnt[b] === count ? 1 : 0;
                t -= cnt[b] === count - 1 ? 1 : 0;
            }
            ans += i === t ? 1 : 0;
        }
    }
    return ans;
}
