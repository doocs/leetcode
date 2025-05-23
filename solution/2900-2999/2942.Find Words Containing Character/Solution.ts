function findWordsContaining(words: string[], x: string): number[] {
    return words.flatMap((w, i) => (w.includes(x) ? [i] : []));
}
