function minSwaps(nums) {
    const n = nums.length;

    const getMin = x => {
        const prefixSum = Array(n + 1).fill(0);
        for (let i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + (nums[i - 1] === x);
        }

        const length = prefixSum[n];
        let ans = Number.POSITIVE_INFINITY;
        for (let l = 0, r = length; r <= n; l++, r++) {
            const min = length - (prefixSum[r] - prefixSum[l]);
            ans = Math.min(ans, min);
        }

        return ans;
    };

    return Math.min(getMin(0), getMin(1));
}
