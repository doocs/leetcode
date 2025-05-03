function numEquivDominoPairs(dominoes: number[][]): number {
    const cnt: number[] = new Array(100).fill(0);
    let ans = 0;

    for (const [a, b] of dominoes) {
        const key = a < b ? a * 10 + b : b * 10 + a;
        ans += cnt[key];
        cnt[key]++;
    }

    return ans;
}
