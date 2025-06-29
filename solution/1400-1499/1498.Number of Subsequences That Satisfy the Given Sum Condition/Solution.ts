function numSubseq(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(1);
    for (let i = 1; i <= n; ++i) {
        f[i] = (f[i - 1] * 2) % mod;
    }

    let ans = 0;
    for (let i = 0; i < n && nums[i] * 2 <= target; ++i) {
        const j = search(nums, target - nums[i], i + 1) - 1;
        if (j >= i) {
            ans = (ans + f[j - i]) % mod;
        }
    }
    return ans;
}

function search(nums: number[], x: number, left: number): number {
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] > x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
