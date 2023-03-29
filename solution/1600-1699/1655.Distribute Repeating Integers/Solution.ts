function canDistribute(nums: number[], quantity: number[]): boolean {
    const m = quantity.length;
    const s: number[] = new Array(1 << m).fill(0);
    for (let i = 1; i < 1 << m; ++i) {
        for (let j = 0; j < m; ++j) {
            if ((i >> j) & 1) {
                s[i] = s[i ^ (1 << j)] + quantity[j];
                break;
            }
        }
    }
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const n = cnt.size;
    const arr: number[] = [];
    for (const [_, v] of cnt) {
        arr.push(v);
    }
    const f: boolean[][] = new Array(n)
        .fill(false)
        .map(() => new Array(1 << m).fill(false));
    for (let i = 0; i < n; ++i) {
        f[i][0] = true;
    }
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < 1 << m; ++j) {
            if (i > 0 && f[i - 1][j]) {
                f[i][j] = true;
                continue;
            }
            for (let k = j; k > 0; k = (k - 1) & j) {
                const ok1: boolean =
                    (i == 0 && j == k) || (i > 0 && f[i - 1][j ^ k]);
                const ok2: boolean = s[k] <= arr[i];
                if (ok1 && ok2) {
                    f[i][j] = true;
                    break;
                }
            }
        }
    }
    return f[n - 1][(1 << m) - 1];
}
