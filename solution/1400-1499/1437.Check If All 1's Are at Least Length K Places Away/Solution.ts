function kLengthApart(nums: number[], k: number): boolean {
    let j = -(k + 1);
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] === 1) {
            if (i - j - 1 < k) {
                return false;
            }
            j = i;
        }
    }
    return true;
}
