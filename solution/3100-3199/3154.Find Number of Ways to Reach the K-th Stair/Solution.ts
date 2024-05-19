function waysToReachStair(k: number): number {
    const f: Map<bigint, number> = new Map();

    const dfs = (i: number, j: number, jump: number): number => {
        if (i > k + 1) {
            return 0;
        }

        const key: bigint = (BigInt(i) << BigInt(32)) | BigInt(jump << 1) | BigInt(j);
        if (f.has(key)) {
            return f.get(key)!;
        }

        let ans: number = 0;
        if (i === k) {
            ans++;
        }

        if (i > 0 && j === 0) {
            ans += dfs(i - 1, 1, jump);
        }

        ans += dfs(i + (1 << jump), 0, jump + 1);
        f.set(key, ans);
        return ans;
    };

    return dfs(1, 0, 0);
}
