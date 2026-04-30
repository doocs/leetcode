function countGoodIntegersOnPath(l: number, r: number, directions: string): number {
    const key = new Array(16).fill(false);
    let row = 0,
        col = 0;
    key[0] = true;
    for (const c of directions) {
        if (c === 'D') {
            row++;
        } else {
            col++;
        }
        key[row * 4 + col] = true;
    }

    let s: string;
    let f: number[][];

    const dfs = (pos: number, last: number, lim: boolean): number => {
        if (pos === 16) {
            return 1;
        }
        if (!lim && f[pos][last] !== -1) {
            return f[pos][last];
        }

        let res = 0;
        const start = key[pos] ? last : 0;
        const end = lim ? parseInt(s[pos]) : 9;

        for (let i = start; i <= end; i++) {
            res += dfs(pos + 1, key[pos] ? i : last, lim && i === end);
        }

        if (!lim) {
            f[pos][last] = res;
        }
        return res;
    };

    const calc = (x: number): number => {
        if (x < 0) {
            return 0;
        }
        s = x.toString().padStart(16, '0');
        f = Array.from({ length: 16 }, () => {
            return new Array(10).fill(-1);
        });
        return dfs(0, 0, true);
    };

    return calc(r) - calc(l - 1);
}
