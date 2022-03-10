function minSwaps(nums: number[]): number {
    const n = nums.length;
    const m = nums.reduce((a, c) => a + c, 0);
    let cnt = nums.reduce((a, c, i) => a + (i < m ? c : 0), 0);
    let ans = cnt;
    for (let i = m; i < m + n; i++) {
        let prev = nums[i - m];
        let post = nums[i % n];
        cnt += post - prev;
        ans = Math.max(cnt, ans);
    }
    return m - ans;
}
