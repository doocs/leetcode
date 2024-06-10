function checkSubarraySum(nums: number[], k: number): boolean {
    const n = nums.length;
    let prefixSum = 0;
    const map = new Map([[0, 0]]);

    for (let i = 0; i < n; i++) {
        prefixSum += nums[i];
        const remainder = prefixSum % k;

        if (!map.has(remainder)) {
            map.set(remainder, i + 1);
        } else if (i - map.get(remainder)! > 0) return true;
    }

    return false;
}
