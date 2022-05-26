function minDeletion(nums: number[]): number {
    const n = nums.length;
    let res = 0;
    let i = 0;
    while (i < n - 1) {
        if (nums[i] === nums[i + 1]) {
            i++;
            res++;
        } else {
            i += 2;
        }
    }
    if ((n - res) % 2 === 1) {
        res++;
    }
    return res;
}
