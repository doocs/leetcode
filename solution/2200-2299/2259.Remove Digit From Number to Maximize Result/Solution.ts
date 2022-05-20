function removeDigit(number: string, digit: string): string {
    let nums = number.split('');
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (nums[i] != digit) continue;
        ans = i;
        if (i + 1 < n && nums[i + 1] > nums[i]) break;
    }
    nums.splice(ans, 1);
    return nums.join('');
}
