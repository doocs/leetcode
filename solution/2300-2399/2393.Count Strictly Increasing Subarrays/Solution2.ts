function countSubarrays(nums: number[]): number {
    let ans = 0;
    let pre = 0;
    let cnt = 0;
    for (const x of nums) {
        if (pre < x) {
            ++cnt;
        } else {
            cnt = 1;
        }
        ans += cnt;
        pre = x;
    }
    return ans;
}
