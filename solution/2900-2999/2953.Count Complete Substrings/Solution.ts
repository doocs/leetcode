function countCompleteSubstrings(word: string, k: number): number {
    const f = (s: string): number => {
        const m = s.length;
        let ans = 0;
        for (let i = 1; i <= 26 && i * k <= m; i++) {
            const l = i * k;
            const cnt: number[] = new Array(26).fill(0);
            for (let j = 0; j < l; j++) {
                cnt[s.charCodeAt(j) - 'a'.charCodeAt(0)]++;
            }
            const freq: { [key: number]: number } = {};
            for (const x of cnt) {
                if (x > 0) {
                    freq[x] = (freq[x] || 0) + 1;
                }
            }
            if (freq[k] === i) {
                ans++;
            }

            for (let j = l; j < m; j++) {
                const a = s.charCodeAt(j) - 'a'.charCodeAt(0);
                const b = s.charCodeAt(j - l) - 'a'.charCodeAt(0);

                freq[cnt[a]]--;
                cnt[a]++;
                freq[cnt[a]] = (freq[cnt[a]] || 0) + 1;

                freq[cnt[b]]--;
                cnt[b]--;
                freq[cnt[b]] = (freq[cnt[b]] || 0) + 1;

                if (freq[k] === i) {
                    ans++;
                }
            }
        }

        return ans;
    };

    let n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && Math.abs(word.charCodeAt(j) - word.charCodeAt(j - 1)) <= 2) {
            j++;
        }
        ans += f(word.substring(i, j));
        i = j;
    }
    return ans;
}
