function maximumRemovals(s: string, p: string, removable: number[]): number {
    const [m, n] = [s.length, p.length];
    let [l, r] = [0, removable.length];
    const rem: boolean[] = Array(m);

    const check = (k: number): boolean => {
        rem.fill(false);
        for (let i = 0; i < k; i++) {
            rem[removable[i]] = true;
        }

        let i = 0,
            j = 0;
        while (i < m && j < n) {
            if (!rem[i] && s[i] === p[j]) {
                j++;
            }
            i++;
        }
        return j === n;
    };

    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
}
