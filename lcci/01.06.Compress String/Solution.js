/**
 * @param {string} S
 * @return {string}
 */
var compressString = function (S) {
    if (!S) return S;
    let p = 0,
        q = 1;
    let res = '';
    while (q < S.length) {
        if (S[p] != S[q]) {
            res += S[p] + (q - p);
            p = q;
        }
        ++q;
    }
    res += S[p] + (q - p);
    return res.length < S.length ? res : S;
};
