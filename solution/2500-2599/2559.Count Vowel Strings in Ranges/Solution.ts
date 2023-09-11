function vowelStrings(words: string[], queries: number[][]): number[] {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u']);
    const n = words.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        if (vowels.has(words[i][0]) && vowels.has(words[i][words[i].length - 1])) {
            s[i + 1] = s[i] + 1;
        } else {
            s[i + 1] = s[i];
        }
    }

    return queries.map(([l, r]) => s[r + 1] - s[l]);
}
