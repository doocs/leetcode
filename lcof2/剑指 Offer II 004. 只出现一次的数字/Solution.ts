function singleNumber(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        let cnt = 0;
        for (const x of nums) {
            cnt += (x >> i) & 1;
        }
        cnt %= 3;
        ans |= cnt << i;
    }
    return ans;
}
