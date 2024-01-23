function minimumPushes(word: string): number {
    const n = word.length;
    let ans = 0;
    let k = 1;
    for (let i = 0; i < ((n / 8) | 0); ++i) {
        ans += k * 8;
        ++k;
    }
    ans += k * (n % 8);
    return ans;
}
