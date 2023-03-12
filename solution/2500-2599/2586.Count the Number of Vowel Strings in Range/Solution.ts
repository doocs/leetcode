function vowelStrings(words: string[], left: number, right: number): number {
    let ans = 0;
    const check: string[] = ['a', 'e', 'i', 'o', 'u'];
    for (let i = left; i <= right; ++i) {
        var w = words[i];
        if (check.includes(w[0]) && check.includes(w[w.length - 1])) {
            ++ans;
        }
    }
    return ans;
}
