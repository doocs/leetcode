function countDominantElements(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < nums.length; i++) {
        let s = 0,
            cnt = 0;
        if (i > 0) {
            s += nums[i - 1];
            cnt++;
        }
        if (i < nums.length - 1) {
            s += nums[i + 1];
            cnt++;
        }
        if (cnt === 0 || nums[i] * cnt >= s) {
            ans++;
        }
    }
    return ans;
}
