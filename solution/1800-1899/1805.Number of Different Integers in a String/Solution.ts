function numDifferentIntegers(word: string): number {
    return new Set(
        word
            .replace(/\D+/g, ' ')
            .trim()
            .split(' ')
            .filter(v => v !== '')
            .map(v => v.replace(/^0+/g, '')),
    ).size;
}
