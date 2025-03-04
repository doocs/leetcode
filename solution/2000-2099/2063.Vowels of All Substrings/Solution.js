/**
 * @param {string} word
 * @return {number}
 */
var countVowels = function (word) {
    const n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (['a', 'e', 'i', 'o', 'u'].includes(word[i])) {
            ans += (i + 1) * (n - i);
        }
    }
    return ans;
};
