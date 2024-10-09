function singleNumber(nums) {
    const sumOfUnique = [...new Set(nums)].reduce((a, b) => a + b, 0);
    const sum = nums.reduce((a, b) => a + b, 0);
    return (sumOfUnique * 3 - sum) / 2;
}
