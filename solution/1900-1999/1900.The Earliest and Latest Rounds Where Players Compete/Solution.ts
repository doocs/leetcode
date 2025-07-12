function earliestAndLatest(n: number, firstPlayer: number, secondPlayer: number): number[] {
    return dfs(firstPlayer - 1, secondPlayer - 1, n);
}

const f: number[][][] = Array.from({ length: 30 }, () =>
    Array.from({ length: 30 }, () => Array(31).fill(0)),
);

function dfs(l: number, r: number, n: number): number[] {
    if (f[l][r][n] !== 0) {
        return decode(f[l][r][n]);
    }
    if (l + r === n - 1) {
        f[l][r][n] = encode(1, 1);
        return [1, 1];
    }

    let min = Number.MAX_SAFE_INTEGER;
    let max = Number.MIN_SAFE_INTEGER;
    const m = n >> 1;

    for (let i = 0; i < 1 << m; i++) {
        const win: boolean[] = Array(n).fill(false);
        for (let j = 0; j < m; j++) {
            if ((i >> j) & 1) {
                win[j] = true;
            } else {
                win[n - 1 - j] = true;
            }
        }

        if (n & 1) {
            win[m] = true;
        }

        win[n - 1 - l] = false;
        win[n - 1 - r] = false;
        win[l] = true;
        win[r] = true;

        let a = 0,
            b = 0,
            c = 0;
        for (let j = 0; j < n; j++) {
            if (j === l) a = c;
            if (j === r) b = c;
            if (win[j]) c++;
        }

        const t = dfs(a, b, c);
        min = Math.min(min, t[0] + 1);
        max = Math.max(max, t[1] + 1);
    }

    f[l][r][n] = encode(min, max);
    return [min, max];
}

function encode(x: number, y: number): number {
    return (x << 8) | y;
}

function decode(val: number): number[] {
    return [val >> 8, val & 255];
}
