/**
 * @param {string} s
 * @return {string}
 */
var sortSentence = function (s) {
    let words = s.split(' ');
    let n = words.length;
    let res = new Array(n);
    for (let word of words) {
        let key = word.slice(-1);
        let val = word.slice(0, -1);
        res[parseInt(key) - 1] = val;
    }
    return res.join(' ');
};
