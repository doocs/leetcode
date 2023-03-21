function numOfStrings(patterns: string[], word: string): number {
    let ans = 0;
    for (const p of patterns) {
        if (word.includes(p)) {
            ++ans;
        }
    }
    return ans;
}
