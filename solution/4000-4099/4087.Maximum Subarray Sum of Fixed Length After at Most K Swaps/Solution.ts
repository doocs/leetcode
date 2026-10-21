function maxSum(nums: number[], length: number, k: number): number {
    const n = nums.length;
    const maxSwaps = Math.min(k, length, n - length);
    let ans = -Infinity;
    for (let i = 0; i <= n - length; ++i) {
        const internal = nums.slice(i, i + length).sort((a, b) => a - b);
        const external = [...nums.slice(0, i), ...nums.slice(i + length)].sort((a, b) => b - a);
        let total = internal.reduce((a, b) => a + b, 0);
        for (let j = 0; j < maxSwaps; ++j) {
            const gain = external[j] - internal[j];
            if (gain <= 0) {
                break;
            }
            total += gain;
        }
        ans = Math.max(ans, total);
    }
    return ans;
}
