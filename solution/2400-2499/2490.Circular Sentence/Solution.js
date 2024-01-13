/**
 * @param {string} sentence
 * @return {boolean}
 */
var isCircularSentence = function (sentence) {
    const ss = sentence.split(' ');
    const n = ss.length;
    for (let i = 0; i < n; ++i) {
        if (ss[i][ss[i].length - 1] !== ss[(i + 1) % n][0]) {
            return false;
        }
    }
    return true;
};
