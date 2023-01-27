function zeroFilledSubarray(nums: number[]): number {
    let ans = 0;
    let cnt = 0;
    for (const v of nums) {
        cnt = v ? 0 : cnt + 1;
        ans += cnt;
    }
    return ans;
}
