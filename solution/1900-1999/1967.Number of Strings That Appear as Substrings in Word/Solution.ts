function numOfStrings(patterns: string[], word: string): number {
    let ans = 0;
    for (let pattern of patterns) {
        if (word.includes(pattern)) {
            ans++;
        }
    }
    return ans;
}
