function sumOfPowers(nums: number[], k: number): number {
    const mod = BigInt(1e9 + 7);
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const f: Map<bigint, bigint> = new Map();
    function dfs(i: number, j: number, k: number, mi: number): bigint {
        if (i >= n) {
            if (k === 0) {
                return BigInt(mi);
            }
            return BigInt(0);
        }
        if (n - i < k) {
            return BigInt(0);
        }
        const key =
            (BigInt(mi) << BigInt(18)) |
            (BigInt(i) << BigInt(12)) |
            (BigInt(j) << BigInt(6)) |
            BigInt(k);
        if (f.has(key)) {
            return f.get(key)!;
        }
        let ans = dfs(i + 1, j, k, mi);
        if (j === n) {
            ans += dfs(i + 1, i, k - 1, mi);
        } else {
            ans += dfs(i + 1, i, k - 1, Math.min(mi, nums[i] - nums[j]));
        }
        ans %= mod;
        f.set(key, ans);
        return ans;
    }

    return Number(dfs(0, n, k, Number.MAX_SAFE_INTEGER));
}
