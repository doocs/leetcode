function minOperations(nums: number[], k: number): number {
    const n = nums.length;

    for (let i = 0; i < n; ++i) {
        nums[i] %= k;
    }

    let ans = Infinity;

    for (let x = 0; x < k; ++x) {
        for (let y = 0; y < k; ++y) {
            if (x !== y) {
                let cnt = 0;

                for (let i = 0; i < n; ++i) {
                    const target = (i & 1) === 0 ? x : y;
                    const diff = Math.abs(target - nums[i]);
                    cnt += Math.min(diff, k - diff);
                }

                ans = Math.min(ans, cnt);
            }
        }
    }

    return ans;
}
