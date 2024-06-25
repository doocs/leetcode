function countCompleteDayPairs(hours: number[]): number {
    const cnt: number[] = Array(24).fill(0);
    let ans: number = 0;
    for (const x of hours) {
        ans += cnt[(24 - (x % 24)) % 24];
        ++cnt[x % 24];
    }
    return ans;
}
