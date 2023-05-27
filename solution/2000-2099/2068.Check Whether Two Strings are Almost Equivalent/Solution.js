/**
 * @param {string} word1
 * @param {string} word2
 * @return {boolean}
 */
var checkAlmostEquivalent = function (word1, word2) {
    const m = new Map();
    for (let i = 0; i < word1.length; i++) {
        m.set(word1[i], (m.get(word1[i]) || 0) + 1);
        m.set(word2[i], (m.get(word2[i]) || 0) - 1);
    }
    for (const v of m.values()) {
        if (Math.abs(v) > 3) {
            return false;
        }
    }
    return true;
};
