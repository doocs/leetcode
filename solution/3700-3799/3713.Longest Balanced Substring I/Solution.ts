function longestBalanced(s: string): number {
    const n = s.length;
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        const cnt: number[] = Array(26).fill(0);
        let [mx, v] = [0, 0];
        for (let j = i; j < n; ++j) {
            const c = s[j].charCodeAt(0) - 97;
            if (++cnt[c] === 1) {
                ++v;
            }
            mx = Math.max(mx, cnt[c]);
            if (mx * v === j - i + 1) {
                ans = Math.max(ans, j - i + 1);
            }
        }
    }
    return ans;
}
