function idealArrays(n: number, maxValue: number): number {
    const mod = 1e9 + 7;

    const c: number[][] = Array.from({ length: n }, () => Array(16).fill(0));
    for (let i = 0; i < n; i++) {
        for (let j = 0; j <= i && j < 16; j++) {
            if (j === 0) {
                c[i][j] = 1;
            } else {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
    }

    const f: number[][] = Array.from({ length: maxValue + 1 }, () => Array(16).fill(0));
    for (let i = 1; i <= maxValue; i++) {
        f[i][1] = 1;
    }

    for (let j = 1; j < 15; j++) {
        for (let i = 1; i <= maxValue; i++) {
            for (let k = 2; k * i <= maxValue; k++) {
                f[k * i][j + 1] = (f[k * i][j + 1] + f[i][j]) % mod;
            }
        }
    }

    let ans = 0;
    for (let i = 1; i <= maxValue; i++) {
        for (let j = 1; j < 16; j++) {
            ans = (ans + f[i][j] * c[n - 1][j - 1]) % mod;
        }
    }

    return ans;
}
