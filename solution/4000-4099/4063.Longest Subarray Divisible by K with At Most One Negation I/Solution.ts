function longestSubarray(nums: number[], k: number): number {
    const f = (nums: number[]): number => {
        const d = new Map<number, number>([[0, -1]]);
        let s = 0,
            res = 0;
        for (let i = 0; i < nums.length; ++i) {
            s = (s + nums[i]) % k;
            if (s < 0) {
                s += k;
            }
            if (d.has(s)) {
                res = Math.max(res, i - d.get(s)!);
            } else {
                d.set(s, i);
            }
        }
        return res;
    };

    let ans = f(nums);
    for (let i = 0; i < nums.length; ++i) {
        nums[i] = -nums[i];
        ans = Math.max(ans, f(nums));
        nums[i] = -nums[i];
    }
    return ans;
}
