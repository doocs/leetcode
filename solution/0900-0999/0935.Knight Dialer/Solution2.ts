const mod = 1e9 + 7;

function knightDialer(n: number): number {
    const base: number[][] = [
        [0, 0, 0, 0, 1, 0, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 1, 0, 1, 0],
        [0, 0, 0, 0, 0, 0, 0, 1, 0, 1],
        [0, 0, 0, 0, 1, 0, 0, 0, 1, 0],
        [1, 0, 0, 1, 0, 0, 0, 0, 0, 1],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 1, 0, 0, 0, 0, 0, 1, 0, 0],
        [0, 0, 1, 0, 0, 0, 1, 0, 0, 0],
        [0, 1, 0, 1, 0, 0, 0, 0, 0, 0],
        [0, 0, 1, 0, 1, 0, 0, 0, 0, 0],
    ];

    const res = pow(base, n - 1);
    let ans = 0;
    for (const x of res[0]) {
        ans = (ans + x) % mod;
    }
    return ans;
}

function mul(a: number[][], b: number[][]): number[][] {
    const m = a.length;
    const n = b[0].length;
    const c: number[][] = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            for (let k = 0; k < b.length; k++) {
                c[i][j] =
                    (c[i][j] + Number((BigInt(a[i][k]) * BigInt(b[k][j])) % BigInt(mod))) % mod;
            }
        }
    }
    return c;
}

function pow(a: number[][], n: number): number[][] {
    const size = a.length;
    let res: number[][] = Array.from({ length: 1 }, () => Array(size).fill(1));

    while (n > 0) {
        if (n % 2 === 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n = Math.floor(n / 2);
    }

    return res;
}
