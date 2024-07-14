function mostCompetitive(nums: number[], k: number): number[] {
    const stk: number[] = [];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        while (stk.length && stk.at(-1)! > nums[i] && stk.length + n - i > k) {
            stk.pop();
        }
        if (stk.length < k) {
            stk.push(nums[i]);
        }
    }
    return stk;
}
