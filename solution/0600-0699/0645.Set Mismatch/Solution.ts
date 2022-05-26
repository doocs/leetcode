function findErrorNums(nums: number[]): number[] {
    let xor = 0;
    for (let i = 0; i < nums.length; ++i) {
        xor ^= (i + 1) ^ nums[i];
    }

    let divide = 1;
    while ((xor & divide) == 0) {
        divide <<= 1;
    }

    let ans1 = 0,
        ans2 = 0;
    for (let i = 0; i < nums.length; ++i) {
        let cur = nums[i];
        if (divide & cur) {
            ans1 ^= cur;
        } else {
            ans2 ^= cur;
        }

        let idx = i + 1;
        if (divide & idx) {
            ans1 ^= idx;
        } else {
            ans2 ^= idx;
        }
    }
    return nums.includes(ans1) ? [ans1, ans2] : [ans2, ans1];
}
