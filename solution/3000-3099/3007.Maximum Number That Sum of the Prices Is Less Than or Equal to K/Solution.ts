function findMaximumNumber(k: number, x: number): number {
    let [l, r] = [1n, 10n ** 17n];
    let num: bigint;
    const f: bigint[][] = Array.from({ length: 65 }, () => Array(65).fill(-1n));

    const dfs = (pos: number, cnt: number, limit: boolean): bigint => {
        if (pos === 0) {
            return BigInt(cnt);
        }
        if (!limit && f[pos][cnt] !== -1n) {
            return f[pos][cnt];
        }
        let ans: bigint = 0n;
        let up: number = 1;
        if (limit) {
            up = Number((num >> BigInt(pos - 1)) & 1n);
        }
        for (let i = 0; i <= up; i++) {
            let v: number = cnt;
            if (i === 1 && pos % x === 0) {
                v++;
            }
            ans += dfs(pos - 1, v, limit && i === up);
        }
        if (!limit) {
            f[pos][cnt] = ans;
        }
        return ans;
    };

    while (l < r) {
        let mid: bigint = (l + r + 1n) >> 1n;
        num = mid;
        let m: number = num.toString(2).length;
        for (let i = 0; i < f.length; i++) {
            f[i].fill(-1n);
        }
        if (dfs(m, 0, true) <= BigInt(k)) {
            l = mid;
        } else {
            r = mid - 1n;
        }
    }
    return Number(l);
}
