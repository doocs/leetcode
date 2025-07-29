function smallestSubarrays(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = Array(n).fill(1);
    const f: number[] = Array(32).fill(-1);

    for (let i = n - 1; i >= 0; i--) {
        let t = 1;
        for (let j = 0; j < 32; j++) {
            if ((nums[i] >> j) & 1) {
                f[j] = i;
            } else if (f[j] !== -1) {
                t = Math.max(t, f[j] - i + 1);
            }
        }
        ans[i] = t;
    }

    return ans;
}
