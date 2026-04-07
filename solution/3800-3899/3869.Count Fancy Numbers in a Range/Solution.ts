function countFancy(l: number, r: number): number {
    const check = (s: number): boolean => {
        if (s < 100) {
            return s % 11 !== 0;
        }
        const mid = Math.floor(s / 10) % 10;
        const last = s % 10;
        return mid > 1 && mid < last;
    };

    let num: string;
    let n: number;
    let f: number[][][][];

    const dfs = (pos: number, s: number, prev: number, st: number, lim: boolean): number => {
        if (pos >= n) {
            if (st !== 3) return 1;
            return check(s) ? 1 : 0;
        }

        if (!lim && f[pos][s][prev][st] !== -1) {
            return f[pos][s][prev][st];
        }

        const up = lim ? Number(num[pos]) : 9;
        let res = 0;

        for (let i = 0; i <= up; i++) {
            let nxtSt = st;

            if (st === 0) {
                if (prev === 0) nxtSt = 0;
                else if (i > prev) nxtSt = 1;
                else if (i < prev) nxtSt = 2;
                else nxtSt = 3;
            } else if (st === 1) {
                if (i > prev) nxtSt = 1;
                else nxtSt = 3;
            } else if (st === 2) {
                if (i < prev) nxtSt = 2;
                else nxtSt = 3;
            } else {
                nxtSt = 3;
            }

            res += dfs(pos + 1, s + i, i, nxtSt, lim && i === up);
        }

        if (!lim) {
            f[pos][s][prev][st] = res;
        }

        return res;
    };

    const calc = (x: number): number => {
        num = x.toString();
        n = num.length;

        f = Array.from({ length: n }, () =>
            Array.from({ length: 9 * n + 1 }, () =>
                Array.from({ length: 10 }, () => Array(4).fill(-1)),
            ),
        );

        return dfs(0, 0, 0, 0, true);
    };

    return calc(r) - calc(l - 1);
}
