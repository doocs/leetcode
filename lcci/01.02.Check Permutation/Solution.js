/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var CheckPermutation = function (s1, s2) {
    let n1 = s1.length,
        n2 = s2.length;
    if (n1 != n2) return false;
    let counter = {};
    for (let i = 0; i < n1; i++) {
        let cur1 = s1.charAt(i),
            cur2 = s2.charAt(i);
        counter[cur1] = (counter[cur1] || 0) + 1;
        counter[cur2] = (counter[cur2] || 0) - 1;
    }
    return Object.values(counter).every(v => v == 0);
};
