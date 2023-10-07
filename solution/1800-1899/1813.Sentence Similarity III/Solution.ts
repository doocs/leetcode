function areSentencesSimilar(sentence1: string, sentence2: string): boolean {
    const words1 = sentence1.split(' ');
    const words2 = sentence2.split(' ');
    if (words1.length < words2.length) {
        return areSentencesSimilar(sentence2, sentence1);
    }
    const [m, n] = [words1.length, words2.length];
    let [i, j] = [0, 0];
    while (i < n && words1[i] === words2[i]) {
        ++i;
    }
    while (j < n && words1[m - 1 - j] === words2[n - 1 - j]) {
        ++j;
    }
    return i + j >= n;
}
