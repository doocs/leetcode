function smallestDistancePair(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let left = 0,
        right = nums[n - 1] - nums[0];
    while (left < right) {
        let mid = (left + right) >> 1;
        let count = 0,
            i = 0;
        for (let j = 0; j < n; j++) {
            // 索引[i, j]距离nums[j]的距离<=mid
            while (nums[j] - nums[i] > mid) {
                i++;
            }
            count += j - i;
        }
        if (count >= k) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
