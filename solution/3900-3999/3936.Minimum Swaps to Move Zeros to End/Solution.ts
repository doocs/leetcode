function minimumSwaps(nums: number[]): number {
    let ans = 0;
    const n = nums.length;

    let i = 0;
    let j = n - 1;

    while (i < j) {
        while (i < n && nums[i] !== 0) {
            ++i;
        }

        while (j > 0 && nums[j] === 0) {
            --j;
        }

        if (i >= j) {
            break;
        }

        ++ans;
        ++i;
        --j;
    }

    return ans;
}
