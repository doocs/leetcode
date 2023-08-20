function isAcronym(words: string[], s: string): boolean {
    return words.map(w => w[0]).join('') === s;
}
