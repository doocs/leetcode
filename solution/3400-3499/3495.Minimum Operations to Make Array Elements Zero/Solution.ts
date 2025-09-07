function minOperations(queries: number[][]): number {
    const f = (x: number): number => {
        let res = 0;
        let p = 1;
        let i = 1;
        while (p <= x) {
            const cnt = Math.min(p * 4 - 1, x) - p + 1;
            res += cnt * i;
            i++;
            p *= 4;
        }
        return res;
    };

    let ans = 0;
    for (const [l, r] of queries) {
        const s = f(r) - f(l - 1);
        const mx = f(r) - f(r - 1);
        ans += Math.max(Math.ceil(s / 2), mx);
    }
    return ans;
}
