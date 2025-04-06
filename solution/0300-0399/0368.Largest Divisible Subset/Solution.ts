function largestDivisibleSubset(nums: number[]): number[] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const f: number[] = Array(n).fill(1);
    let k = 0;

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[i] % nums[j] === 0) {
                f[i] = Math.max(f[i], f[j] + 1);
            }
        }
        if (f[k] < f[i]) {
            k = i;
        }
    }

    let m = f[k];
    const ans: number[] = [];
    for (let i = k; m > 0; --i) {
        if (nums[k] % nums[i] === 0 && f[i] === m) {
            ans.push(nums[i]);
            k = i;
            --m;
        }
    }

    return ans;
}
