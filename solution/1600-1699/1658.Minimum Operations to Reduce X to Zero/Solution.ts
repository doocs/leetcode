function minOperations(nums: number[], x: number): number {
    const total = nums.reduce((a, c) => a + c, 0);
    if (total < x) return -1;
    // 前缀和 + 哈希表, 求何为total - x的最长子序列
    const n = nums.length;
    const target = total - x;
    let hashMap = new Map();
    hashMap.set(0, -1);
    let pre = 0;
    let ans = -1;
    for (let right = 0; right < n; right++) {
        pre += nums[right];
        if (!hashMap.has(pre)) {
            hashMap.set(pre, right);
        }
        if (hashMap.has(pre - target)) {
            let left = hashMap.get(pre - target);
            ans = Math.max(right - left, ans);
        }
    }
    return ans == -1 ? -1 : n - ans;
}
