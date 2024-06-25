function longestSubarray(nums: number[], limit: number): number {
    const n = nums.length;
    let [h1, t1] = [0, -1];
    let [h2, t2] = [0, -1];
    let l = 0;
    const maxq = Array(n);
    const minq = Array(n);
    for (let r = 0; r < n; ++r) {
        while (h1 <= t1 && nums[maxq[t1]] < nums[r]) {
            --t1;
        }
        while (h2 <= t2 && nums[minq[t2]] > nums[r]) {
            --t2;
        }
        maxq[++t1] = r;
        minq[++t2] = r;
        if (nums[maxq[h1]] - nums[minq[h2]] > limit) {
            ++l;
            if (maxq[h1] < l) {
                ++h1;
            }
            if (minq[h2] < l) {
                ++h2;
            }
        }
    }
    return n - l;
}
