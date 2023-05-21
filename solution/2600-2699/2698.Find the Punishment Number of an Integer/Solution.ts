function punishmentNumber(n: number): number {
    const check = (s: string, i: number, x: number): boolean => {
        const m = s.length;
        if (i >= m) {
            return x === 0;
        }
        let y = 0;
        for (let j = i; j < m; ++j) {
            y = y * 10 + Number(s[j]);
            if (y > x) {
                break;
            }
            if (check(s, j + 1, x - y)) {
                return true;
            }
        }
        return false;
    };
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        const x = i * i;
        const s = x.toString();
        if (check(s, 0, i)) {
            ans += x;
        }
    }
    return ans;
}
