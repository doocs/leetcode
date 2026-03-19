function countSubarrays(nums: number[], k: number): number {
    let ans = 0;
    const q1: number[] = [];
    const q2: number[] = [];
    let h1 = 0,
        t1 = 0;
    let h2 = 0,
        t2 = 0;
    let l = 0;
    for (let r = 0; r < nums.length; r++) {
        const x = nums[r];
        while (h1 < t1 && nums[q1[t1 - 1]] <= x) {
            t1--;
        }
        while (h2 < t2 && nums[q2[t2 - 1]] >= x) {
            t2--;
        }
        q1[t1++] = r;
        q2[t2++] = r;
        while (l < r && (nums[q1[h1]] - nums[q2[h2]]) * (r - l + 1) > k) {
            l++;
            if (q1[h1] < l) {
                h1++;
            }
            if (q2[h2] < l) {
                h2++;
            }
        }
        ans += r - l + 1;
    }
    return ans;
}
