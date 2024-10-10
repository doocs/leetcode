function maxWidthRamp(nums: number[]): number {
    let [ans, n] = [0, nums.length];
    const stk: number[] = [];

    for (let i = 0; i < n - 1; i++) {
        if (stk.length === 0 || nums[stk.at(-1)!] > nums[i]) {
            stk.push(i);
        }
    }

    for (let i = n - 1; i >= 0; i--) {
        while (stk.length && nums[stk.at(-1)!] <= nums[i]) {
            ans = Math.max(ans, i - stk.pop()!);
        }
        if (stk.length === 0) break;
    }

    return ans;
}
