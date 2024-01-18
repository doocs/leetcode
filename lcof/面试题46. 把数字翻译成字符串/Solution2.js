/**
 * @param {number} num
 * @return {number}
 */
var translateNum = function (num) {
    const s = num.toString();
    const n = s.length;
    let a = 1;
    let b = 1;
    for (let i = 1; i < n; ++i) {
        let c = b;
        if (s[i - 1] === '1' || (s[i - 1] === '2' && s[i] < '6')) {
            c += a;
        }
        a = b;
        b = c;
    }
    return b;
};
