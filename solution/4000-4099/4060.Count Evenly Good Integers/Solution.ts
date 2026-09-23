function countEvenlyGoodIntegers(l: number, r: number): number {
    let s: string;
    let f: number[][][];

    const calc = (x: number): number => {
        s = String(x);
        f = Array.from({ length: s.length }, () =>
            Array.from({ length: 2 }, () => Array(2).fill(-1)),
        );

        const dfs = (pos: number, st: number, lim: boolean): number => {
            if (pos >= s.length) {
                return st ^ 1;
            }
            const k = lim ? 1 : 0;
            if (f[pos][st][k] !== -1) {
                return f[pos][st][k];
            }
            const up = lim ? Number(s[pos]) : 9;
            let res = 0;
            for (let i = 0; i <= up; ++i) {
                res += dfs(pos + 1, (st + ((i & 1) ^ 1)) % 2, lim && i === up);
            }
            return (f[pos][st][k] = res);
        };

        return dfs(0, 0, true);
    };

    return calc(r) - calc(l - 1);
}
