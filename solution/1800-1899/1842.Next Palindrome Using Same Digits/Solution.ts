function nextPalindrome(num: string): string {
    const nums = num.split('');
    const n = nums.length;
    if (!nextPermutation(nums)) {
        return '';
    }
    for (let i = 0; i < n >> 1; ++i) {
        nums[n - 1 - i] = nums[i];
    }
    return nums.join('');
}

function nextPermutation(nums: string[]): boolean {
    const n = nums.length >> 1;
    let i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    if (i < 0) {
        return false;
    }
    let j = n - 1;
    while (j >= 0 && nums[i] >= nums[j]) {
        j--;
    }
    [nums[i], nums[j]] = [nums[j], nums[i]];
    for (i = i + 1, j = n - 1; i < j; ++i, --j) {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
    return true;
}
