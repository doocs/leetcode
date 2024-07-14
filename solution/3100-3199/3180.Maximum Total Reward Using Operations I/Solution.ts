function maxTotalReward(rewardValues: number[]): number {
    rewardValues.sort((a, b) => a - b);
    const search = (x: number): number => {
        let [l, r] = [0, rewardValues.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (rewardValues[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const f: number[] = Array(rewardValues.at(-1)! << 1).fill(-1);
    const dfs = (x: number): number => {
        if (f[x] !== -1) {
            return f[x];
        }
        let ans = 0;
        for (let i = search(x); i < rewardValues.length; ++i) {
            ans = Math.max(ans, rewardValues[i] + dfs(x + rewardValues[i]));
        }
        return (f[x] = ans);
    };
    return dfs(0);
}
