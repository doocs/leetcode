function countMajoritySubarrays(nums: number[], target: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let cnt: number = 0;
        for (let j = i; j < n; ++j) {
            const k = j - i + 1;
            cnt += nums[j] == target ? 1 : 0;
            if (cnt * 2 > k) {
                ++ans;
            }
        }
    }
    return ans;
}
