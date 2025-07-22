function largestVariance(s: string): number {
    const n: number = s.length;
    let ans: number = 0;
    for (let a = 97; a <= 122; ++a) {
        for (let b = 97; b <= 122; ++b) {
            if (a === b) {
                continue;
            }
            const f: number[] = [0, -n];
            for (let i = 0; i < n; ++i) {
                if (s.charCodeAt(i) === a) {
                    f[0]++;
                    f[1]++;
                } else if (s.charCodeAt(i) === b) {
                    f[1] = Math.max(f[0] - 1, f[1] - 1);
                    f[0] = 0;
                }
                ans = Math.max(ans, f[1]);
            }
        }
    }
    return ans;
}
