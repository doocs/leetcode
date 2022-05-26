/**
 * @param {string} word
 * @return {number}
 */
var wonderfulSubstrings = function (word) {
    let counter = new Array(1 << 10).fill(0);
    counter[0] = 1;
    let state = 0;
    let ans = 0;
    for (let c of word) {
        state ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        ans += counter[state];
        for (let i = 0; i < 10; ++i) {
            ans += counter[state ^ (1 << i)];
        }
        ++counter[state];
    }
    return ans;
};
