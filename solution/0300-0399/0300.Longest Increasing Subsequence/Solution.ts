function lengthOfLIS(nums: number[]): number {
    const n = nums.length;
    let d = new Array(n + 1);
    d[1] = nums[0];
    let size = 1;
    for (let i = 1; i < n; ++i) {
        if (nums[i] > d[size]) {
            d[++size] = nums[i];
        } else {
            let left = 1,
                right = size;
            while (left < right) {
                const mid = (left + right) >> 1;
                if (d[mid] >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            const p = d[left] >= nums[i] ? left : 1;
            d[p] = nums[i];
        }
    }
    return size;
}
