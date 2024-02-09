function numberCount(a: number, b: number): number {
    let num: string = b.toString();
    const f: number[][] = Array(num.length)
        .fill(0)
        .map(() => Array(1 << 10).fill(-1));
    const dfs: (pos: number, mask: number, limit: boolean) => number = (pos, mask, limit) => {
        if (pos >= num.length) {
            return mask ? 1 : 0;
        }
        if (!limit && f[pos][mask] !== -1) {
            return f[pos][mask];
        }
        const up: number = limit ? +num[pos] : 9;
        let ans: number = 0;
        for (let i = 0; i <= up; i++) {
            if ((mask >> i) & 1) {
                continue;
            }
            let nxt: number = mask | (1 << i);
            if (mask === 0 && i === 0) {
                nxt = 0;
            }
            ans += dfs(pos + 1, nxt, limit && i === up);
        }
        if (!limit) {
            f[pos][mask] = ans;
        }
        return ans;
    };

    const y: number = dfs(0, 0, true);
    num = (a - 1).toString();
    f.forEach(v => v.fill(-1));
    const x: number = dfs(0, 0, true);
    return y - x;
}
