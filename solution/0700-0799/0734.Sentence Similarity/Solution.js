/**
 * @param {string[]} sentence1
 * @param {string[]} sentence2
 * @param {string[][]} similarPairs
 * @return {boolean}
 */
var areSentencesSimilar = function (sentence1, sentence2, similarPairs) {
    if (sentence1.length !== sentence2.length) {
        return false;
    }
    const s = new Set();
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
};
