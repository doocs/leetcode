function pivotArray(nums: number[], pivot: number): number[] {
    const n = nums.length;
    const res = Array(n).fill(pivot);

    for (let i = 0, l = 0, j = n - 1, r = n - 1; i < n; i++, j--) {
        if (nums[i] < pivot) res[l++] = nums[i];
        if (nums[j] > pivot) res[r--] = nums[j];
    }

    return res;
}
