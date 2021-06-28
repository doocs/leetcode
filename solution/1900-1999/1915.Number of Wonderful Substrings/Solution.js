/**
 * @param {string} word
 * @return {number}
 */
 var wonderfulSubstrings = function(word) {
    let n = 1 << 10;
    let counts = new Array(n).fill(0);
    counts[0] = 1;
    let pre = 0;
    let ans = 0;
    for (let c of word) {
        let cur = c.charCodeAt(0) - 'a'.charCodeAt(0);
        pre ^= (1 << cur);
        ans += counts[pre];
        for (let i = 1; i < n; i <<= 1) {
            ans += counts[pre ^ i];
        }
        ++counts[pre];
    }
    return ans;
};