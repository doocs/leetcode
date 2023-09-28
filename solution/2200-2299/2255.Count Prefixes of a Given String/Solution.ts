function countPrefixes(words: string[], s: string): number {
    return words.filter(w => s.startsWith(w)).length;
}
