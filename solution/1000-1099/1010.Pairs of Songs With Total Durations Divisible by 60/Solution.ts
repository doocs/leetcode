function numPairsDivisibleBy60(time: number[]): number {
    const cnt: number[] = new Array(60).fill(0);
    for (const t of time) {
        ++cnt[t % 60];
    }
    let ans = 0;
    for (let x = 1; x < 30; ++x) {
        ans += cnt[x] * cnt[60 - x];
    }
    ans += (cnt[0] * (cnt[0] - 1)) / 2;
    ans += (cnt[30] * (cnt[30] - 1)) / 2;
    return ans;
}
