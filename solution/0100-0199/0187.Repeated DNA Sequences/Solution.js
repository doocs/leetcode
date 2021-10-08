/**
 * @param {string} s
 * @return {string[]}
 */
 var findRepeatedDnaSequences = function(s) {
    const n = s.length - 10;
    let cnt = new Map();
    let ans = [];
    for (let i = 0; i <= n; ++i) {
        let sub = s.slice(i, i + 10);
        cnt[sub] = (cnt[sub] || 0) + 1;
        if (cnt[sub] == 2) {
            ans.push(sub);
        }
    }
    return ans;
};