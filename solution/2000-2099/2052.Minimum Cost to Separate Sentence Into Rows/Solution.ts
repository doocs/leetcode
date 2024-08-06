function minimumCost(sentence: string, k: number): number {
    const s: number[] = [0];
    for (const w of sentence.split(' ')) {
        s.push(s.at(-1)! + w.length);
    }
    const n = s.length - 1;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (s[n] - s[i] + n - i - 1 <= k) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        let ans = Infinity;
        for (let j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
            const m = s[j] - s[i] + j - i - 1;
            ans = Math.min(ans, dfs(j) + (k - m) ** 2);
        }
        return (f[i] = ans);
    };
    return dfs(0);
}
