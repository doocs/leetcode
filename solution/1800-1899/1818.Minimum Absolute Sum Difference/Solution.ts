function minAbsoluteSumDiff(nums1: number[], nums2: number[]): number {
    const mod = 10 ** 9 + 7;
    const nums = [...nums1];
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
    }
    let mx = 0;
    for (let i = 0; i < n; ++i) {
        const d1 = Math.abs(nums1[i] - nums2[i]);
        let d2 = 1 << 30;
        let j = search(nums, nums2[i]);
        if (j < n) {
            d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
        }
        if (j) {
            d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
        }
        mx = Math.max(mx, d1 - d2);
    }
    return (s - mx + mod) % mod;
}

function search(nums: number[], x: number): number {
    let left = 0;
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
