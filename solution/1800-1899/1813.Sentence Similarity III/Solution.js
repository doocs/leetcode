function areSentencesSimilar(sentence1, sentence2) {
    const [words1, words2] = [sentence1.split(' '), sentence2.split(' ')];
    const [m, n] = [words1.length, words2.length];

    if (m > n) return areSentencesSimilar(sentence2, sentence1);

    let [l, r] = [0, 0];
    for (let i = 0; i < n; i++) {
        if (l === i && words1[i] === words2[i]) l++;
        if (r === i && words2[n - i - 1] === words1[m - r - 1]) r++;
    }

    return l + r >= m;
}
