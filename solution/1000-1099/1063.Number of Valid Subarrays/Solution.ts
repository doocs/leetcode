function validSubarrays(nums: number[]): number {
    const n = nums.length;
    const stk: number[] = [];
    let ans = 0;
    for (let i = n - 1; ~i; --i) {
        while (stk.length && nums[stk.at(-1)!] >= nums[i]) {
            stk.pop();
        }
        ans += (stk.at(-1) ?? n) - i;
        stk.push(i);
    }
    return ans;
}
