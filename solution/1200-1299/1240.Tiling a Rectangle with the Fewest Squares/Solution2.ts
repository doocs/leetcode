function tilingRectangle(n: number, m: number): number {
    let ans = n * m;
    const filled: number[] = new Array(n).fill(0);
    const dfs = (i: number, j: number, t: number) => {
        if (j === m) {
            ++i;
            j = 0;
        }
        if (i === n) {
            ans = t;
            return;
        }
        if ((filled[i] >> j) & 1) {
            dfs(i, j + 1, t);
        } else if (t + 1 < ans) {
            let [r, c] = [0, 0];
            for (let k = i; k < n; ++k) {
                if ((filled[k] >> j) & 1) {
                    break;
                }
                ++r;
            }
            for (let k = j; k < m; ++k) {
                if ((filled[i] >> k) & 1) {
                    break;
                }
                ++c;
            }
            const mx = Math.min(r, c);
            for (let x = i; x < i + mx; ++x) {
                for (let y = j; y < j + mx; ++y) {
                    filled[x] |= 1 << y;
                }
            }
            for (let w = mx; w > 0; --w) {
                dfs(i, j + w, t + 1);
                for (let k = 0; k < w; ++k) {
                    filled[i + w - 1] ^= 1 << (j + k);
                    if (k < w - 1) {
                        filled[i + k] ^= 1 << (j + w - 1);
                    }
                }
            }
        }
    };
    dfs(0, 0, 0);
    return ans;
}
