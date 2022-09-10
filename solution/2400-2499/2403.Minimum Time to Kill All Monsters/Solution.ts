function minimumTime(power: number[]): number {
    const n = power.length;
    const f = new Array(1 << n).fill(-1);
    function dfs(mask) {
        if (f[mask] != -1) {
            return f[mask];
        }
        const cnt = bitCount(mask);
        if (cnt == n) {
            return 0;
        }
        let ans = Infinity;
        for (let i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                continue;
            }
            ans = Math.min(
                ans,
                dfs(mask | (1 << i)) + Math.ceil(power[i] / (cnt + 1)),
            );
        }
        f[mask] = ans;
        return ans;
    }
    return dfs(0);
}

function bitCount(x) {
    let cnt = 0;
    for (let i = 0; i < 32; ++i) {
        if ((x >> i) & 1) {
            ++cnt;
        }
    }
    return cnt;
}
