function countSubarrays(nums: number[], minK: number, maxK: number): number {
    let ans = 0;
    let l = 0;
    for (let r = 0; r < nums.length; r++) {
        const x = nums[r];
        if (x < minK || x > maxK) {
            l = r + 1;
        } else {
            ans += r - l + 1;
        }
    }
    return ans;
}
