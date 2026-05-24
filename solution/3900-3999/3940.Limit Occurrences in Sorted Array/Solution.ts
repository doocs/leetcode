function limitOccurrences(nums: number[], k: number): number[] {
    const n = nums.length;
    let cnt = 1;
    let l = 1;

    for (let r = 1; r < n; r++) {
        if (nums[r] !== nums[r - 1]) {
            cnt = 1;
        } else {
            cnt++;
        }

        if (cnt <= k) {
            nums[l] = nums[r];
            l++;
        }
    }

    return nums.slice(0, l);
}
