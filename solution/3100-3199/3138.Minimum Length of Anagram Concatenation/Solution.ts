function minAnagramLength(s: string): number {
    const n = s.length;
    const cnt: Record<string, number> = {};
    for (let i = 0; i < n; i++) {
        cnt[s[i]] = (cnt[s[i]] || 0) + 1;
    }
    const check = (k: number): boolean => {
        for (let i = 0; i < n; i += k) {
            const cnt1: Record<string, number> = {};
            for (let j = i; j < i + k; j++) {
                cnt1[s[j]] = (cnt1[s[j]] || 0) + 1;
            }
            for (const [c, v] of Object.entries(cnt)) {
                if (cnt1[c] * ((n / k) | 0) !== v) {
                    return false;
                }
            }
        }
        return true;
    };
    for (let i = 1; ; ++i) {
        if (n % i === 0 && check(i)) {
            return i;
        }
    }
}
