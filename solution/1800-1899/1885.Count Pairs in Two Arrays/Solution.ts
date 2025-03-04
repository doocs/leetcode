function countPairs(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const nums: number[] = [];
    for (let i = 0; i < n; ++i) {
        nums.push(nums1[i] - nums2[i]);
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    let [l, r] = [0, n - 1];
    while (l < r) {
        while (l < r && nums[l] + nums[r] <= 0) {
            ++l;
        }
        ans += r - l;
        --r;
    }
    return ans;
}
