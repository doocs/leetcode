/**
 * @param {string} S
 * @return {string}
 */
var compressString = function (S) {
    const n = S.length;
    const t = [];
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && S.charAt(j) === S.charAt(i)) {
            ++j;
        }
        t.push(S.charAt(i), j - i);
        i = j;
    }
    return t.length < n ? t.join('') : S;
};
