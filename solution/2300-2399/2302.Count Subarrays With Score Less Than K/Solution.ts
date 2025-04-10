function countSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        let [l, r] = [0, i];
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            if ((s[i] - s[i - mid]) * mid < k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        ans += l;
    }
    return ans;
}
