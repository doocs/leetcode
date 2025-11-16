function countDistinct(n: number): number {
    const s = n.toString();
    const m = s.length;

    const f: number[][][][] = Array.from({ length: m }, () =>
        Array.from({ length: 2 }, () => Array.from({ length: 2 }, () => Array(2).fill(-1))),
    );

    const dfs = (i: number, zero: number, lead: number, limit: number): number => {
        if (i === m) {
            return zero === 0 && lead === 0 ? 1 : 0;
        }

        if (limit === 0 && f[i][zero][lead][limit] !== -1) {
            return f[i][zero][lead][limit];
        }

        const up = limit === 1 ? parseInt(s[i]) : 9;
        let ans = 0;
        for (let d = 0; d <= up; d++) {
            const nxtZero = zero === 1 || (d === 0 && lead === 0) ? 1 : 0;
            const nxtLead = lead === 1 && d === 0 ? 1 : 0;
            const nxtLimit = limit === 1 && d === up ? 1 : 0;
            ans += dfs(i + 1, nxtZero, nxtLead, nxtLimit);
        }

        if (limit === 0) {
            f[i][zero][lead][limit] = ans;
        }
        return ans;
    };

    return dfs(0, 0, 1, 1);
}
