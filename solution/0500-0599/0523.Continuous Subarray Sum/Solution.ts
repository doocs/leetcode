function checkSubarraySum(nums: number[], k: number): boolean {
    const d: Record<number, number> = { 0: -1 };
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s = (s + nums[i]) % k;
        if (!d.hasOwnProperty(s)) {
            d[s] = i;
        } else if (i - d[s] > 1) {
            return true;
        }
    }
    return false;
}
