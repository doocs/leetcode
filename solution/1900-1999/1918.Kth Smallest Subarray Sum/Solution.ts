function kthSmallestSubarraySum(nums: number[], k: number): number {
    let l = Math.min(...nums);
    let r = nums.reduce((sum, x) => sum + x, 0);

    const f = (s: number): number => {
        let cnt = 0;
        let t = 0;
        let j = 0;

        for (let i = 0; i < nums.length; i++) {
            t += nums[i];
            while (t > s) {
                t -= nums[j];
                j++;
            }
            cnt += i - j + 1;
        }
        return cnt;
    };

    while (l < r) {
        const mid = (l + r) >> 1;
        if (f(mid) >= k) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
