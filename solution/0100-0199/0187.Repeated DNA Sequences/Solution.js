/**
 * @param {string} s
 * @return {string[]}
 */
 var findRepeatedDnaSequences = function(s) {
    let n = 10;
    let subs = new Set();
    let res = new Set();
    for (let i = 0; i < s.length - n + 1; i++) {
        let sub = s.slice(i, i + n);
        if (subs.has(sub)) {
            res.add(sub);
        }
        subs.add(sub);
    }
    return [...res];
};