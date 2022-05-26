function getMaxLen(nums: number[]): number {
    // 连续正数计数n1, 连续负数计数n2
    let n1 = nums[0] > 0 ? 1 : 0,
        n2 = nums[0] < 0 ? 1 : 0;
    let ans = n1;
    for (let i = 1; i < nums.length; ++i) {
        let cur = nums[i];
        if (cur == 0) {
            (n1 = 0), (n2 = 0);
        } else if (cur > 0) {
            ++n1;
            n2 = n2 > 0 ? n2 + 1 : 0;
        } else {
            let t1 = n1,
                t2 = n2;
            n1 = t2 > 0 ? t2 + 1 : 0;
            n2 = t1 + 1;
        }
        ans = Math.max(ans, n1);
    }
    return ans;
}
