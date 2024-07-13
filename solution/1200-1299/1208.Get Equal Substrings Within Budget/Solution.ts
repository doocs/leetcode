function equalSubstring(s: string, t: string, maxCost: number): number {
    const n = s.length;
    const f = Array(n + 1).fill(0);

    for (let i = 0; i < n; i++) {
        f[i + 1] = f[i] + Math.abs(s.charCodeAt(i) - t.charCodeAt(i));
    }

    const check = (x: number): boolean => {
        for (let i = 0; i + x - 1 < n; i++) {
            if (f[i + x] - f[i] <= maxCost) {
                return true;
            }
        }
        return false;
    };

    let l = 0,
        r = n;
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
