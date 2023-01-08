function prefixCount(words: string[], pref: string): number {
    return words.reduce((r, s) => (r += s.startsWith(pref) ? 1 : 0), 0);
}
