function removeAlmostEqualCharacters(word: string): number {
    let ans = 0;
    for (let i = 1; i < word.length; ++i) {
        if (Math.abs(word.charCodeAt(i) - word.charCodeAt(i - 1)) < 2) {
            ++ans;
            ++i;
        }
    }
    return ans;
}
