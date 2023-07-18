function runeReserve(runes: number[]): number {
    runes.sort((a, b) => a - b);
    let ans = 0;
    let i = 0;
    for (let j = 0; j < runes.length; ++j) {
        if (j > 0 && runes[j] - runes[j - 1] > 1) {
            i = j;
        } else {
            ans = Math.max(ans, j - i + 1);
        }
    }
    return ans;
}
