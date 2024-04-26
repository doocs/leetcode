function reverseBits(num: number): number {
    let ans = 0;
    let cnt = 0;
    for (let i = 0, j = 0; i < 32; ++i) {
        cnt += ((num >> i) & 1) ^ 1;
        for (; cnt > 1; ++j) {
            cnt -= ((num >> j) & 1) ^ 1;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
