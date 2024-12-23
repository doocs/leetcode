function minLength(s: string, numOps: number): number {
    const n = s.length;
    const check = (m: number): boolean => {
        let cnt = 0;
        if (m === 1) {
            const t = '01';
            for (let i = 0; i < n; ++i) {
                if (s[i] === t[i & 1]) {
                    ++cnt;
                }
            }
            cnt = Math.min(cnt, n - cnt);
        } else {
            let k = 0;
            for (let i = 0; i < n; ++i) {
                ++k;
                if (i === n - 1 || s[i] !== s[i + 1]) {
                    cnt += Math.floor(k / (m + 1));
                    k = 0;
                }
            }
        }
        return cnt <= numOps;
    };
    let [l, r] = [1, n];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
