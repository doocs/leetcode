function longestOnes(nums: number[], k: number): number {
    const n = nums.length;
    let l = 0;
    let res = k;
    const count = [0, 0];
    for (let r = 0; r < n; r++) {
        count[nums[r]]++;
        res = Math.max(res, r - l);
        while (count[0] > k) {
            count[nums[l]]--;
            l++;
        }
    }
    return Math.max(res, n - l);
}
