function minimalKSum(nums: number[], k: number): number {
    nums.push(...[0, 2 * 10 ** 9]);
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < nums.length - 1; ++i) {
        const m = Math.max(0, Math.min(k, nums[i + 1] - nums[i] - 1));
        ans += Number((BigInt(nums[i] + 1 + nums[i] + m) * BigInt(m)) / BigInt(2));
        k -= m;
    }
    return ans;
}
