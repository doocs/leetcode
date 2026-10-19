function singleNumber(nums) {
    let ans = 0;
    for (let i = 0; i < 32; i++) {
        const cnt = nums.reduce((r, num) => r + ((num >> i) & 1), 0);
        ans |= (cnt % 3) << i;
    }
    return ans;
}
