/**
 * @param {string} s
 * @param {number} k
 * @return {string}
 */
var digitSum = function (s, k) {
    while (s.length > k) {
        const t = [];
        for (let i = 0; i < s.length; i += k) {
            const x = s
                .slice(i, i + k)
                .split('')
                .reduce((a, b) => a + +b, 0);
            t.push(x);
        }
        s = t.join('');
    }
    return s;
};
