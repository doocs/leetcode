function minBitFlips(start: number, goal: number): number {
    let tmp = start ^ goal;
    let ans = 0;
    while (tmp !== 0) {
        ans += tmp & 1;
        tmp >>= 1;
    }
    return ans;
}
