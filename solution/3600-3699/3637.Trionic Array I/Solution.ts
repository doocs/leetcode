function isTrionic(nums: number[]): boolean {
    const n = nums.length;
    let p = 0;
    while (p < n - 2 && nums[p] < nums[p + 1]) {
        p++;
    }
    if (p === 0) {
        return false;
    }
    let q = p;
    while (q < n - 1 && nums[q] > nums[q + 1]) {
        q++;
    }
    if (q === p || q === n - 1) {
        return false;
    }
    while (q < n - 1 && nums[q] < nums[q + 1]) {
        q++;
    }
    return q === n - 1;
}
