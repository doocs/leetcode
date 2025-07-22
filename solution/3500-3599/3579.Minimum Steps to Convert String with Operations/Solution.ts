function minOperations(word1: string, word2: string): number {
    const n = word1.length;
    const f = Array(n + 1).fill(Number.MAX_SAFE_INTEGER);
    f[0] = 0;

    function calc(l: number, r: number, rev: boolean): number {
        const cnt: number[][] = Array.from({ length: 26 }, () => Array(26).fill(0));
        let res = 0;

        for (let i = l; i <= r; i++) {
            const j = rev ? r - (i - l) : i;
            const a = word1.charCodeAt(j) - 97;
            const b = word2.charCodeAt(i) - 97;

            if (a !== b) {
                if (cnt[b][a] > 0) {
                    cnt[b][a]--;
                } else {
                    cnt[a][b]++;
                    res++;
                }
            }
        }

        return res;
    }

    for (let i = 1; i <= n; i++) {
        for (let j = 0; j < i; j++) {
            const a = calc(j, i - 1, false);
            const b = 1 + calc(j, i - 1, true);
            const t = Math.min(a, b);
            f[i] = Math.min(f[i], f[j] + t);
        }
    }

    return f[n];
}
