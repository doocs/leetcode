function maximizeSum(nums: number[], k: number): number {
    const f = (x: number): number => {
        const cnt = Array(10).fill(0);
        while (x > 0) {
            const v = x % 10;
            x = Math.floor(x / 10);
            cnt[v]++;
        }
        let y = 0;
        for (let i = 9; i >= 0; i--) {
            for (let j = 0; j < cnt[i]; j++) {
                y = y * 10 + i;
            }
        }
        return y;
    };
    const n = nums.length;
    const t = nums.map(x => f(x));
    const ids = Array.from({ length: n }, (_, i) => i);
    ids.sort((i, j) => t[j] - nums[j] - (t[i] - nums[i]));
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const x = nums[ids[i]];
        ans += i < k ? t[ids[i]] : x;
    }
    return ans;
}
