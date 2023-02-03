function reversePairs(nums: number[]): number {
    const mergeSort = (l: number, r: number): number => {
        if (l >= r) {
            return 0;
        }
        const mid = (l + r) >> 1;
        let ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        let i = l;
        let j = mid + 1;
        const t: number[] = [];
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                t.push(nums[i++]);
            } else {
                ans += mid - i + 1;
                t.push(nums[j++]);
            }
        }
        while (i <= mid) {
            t.push(nums[i++]);
        }
        while (j <= r) {
            t.push(nums[j++]);
        }
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        return ans;
    };
    return mergeSort(0, nums.length - 1);
}
