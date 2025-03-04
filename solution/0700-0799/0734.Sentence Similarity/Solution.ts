function areSentencesSimilar(
    sentence1: string[],
    sentence2: string[],
    similarPairs: string[][],
): boolean {
    if (sentence1.length !== sentence2.length) {
        return false;
    }
    const s = new Set<string>();
    for (const [x, y] of similarPairs) {
        s.add(x + '#' + y);
        s.add(y + '#' + x);
    }
    for (let i = 0; i < sentence1.length; i++) {
        if (sentence1[i] !== sentence2[i] && !s.has(sentence1[i] + '#' + sentence2[i])) {
            return false;
        }
    }
    return true;
}
