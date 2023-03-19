function numDupDigitsAtMostN(n: number): number {
    return n - f(n);
}

function f(n: number): number {
    const nums: number[] = [];
    let i = -1;
    for (; n; n = Math.floor(n / 10)) {
        nums[++i] = n % 10;
    }
    const dp = Array.from({ length: 11 }, () => Array(1 << 11).fill(-1));
    const dfs = (
        pos: number,
        mask: number,
        lead: boolean,
        limit: boolean,
    ): number => {
        if (pos < 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] !== -1) {
            return dp[pos][mask];
        }
        const up = limit ? nums[pos] : 9;
        let ans = 0;
        for (let i = 0; i <= up; ++i) {
            if ((mask >> i) & 1) {
                continue;
            }
            if (lead && i === 0) {
                ans += dfs(pos - 1, mask, lead, limit && i === up);
            } else {
                ans += dfs(pos - 1, mask | (1 << i), false, limit && i === up);
            }
        }
        if (!lead && !limit) {
            dp[pos][mask] = ans;
        }
        return ans;
    };
    return dfs(i, 0, true, true);
}
