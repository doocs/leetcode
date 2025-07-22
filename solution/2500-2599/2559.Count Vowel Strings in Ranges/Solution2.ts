function vowelStrings(words: string[], queries: number[][]): number[] {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u']);
    const s = new Array(words.length + 1).fill(0);

    words.forEach((w, i) => {
        const x = +(vowels.has(w[0]) && vowels.has(w.at(-1)!));
        s[i + 1] = s[i] + x;
    });

    return queries.map(([l, r]) => s[r + 1] - s[l]);
}
