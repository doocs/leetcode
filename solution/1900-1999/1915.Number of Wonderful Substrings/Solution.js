/**
 * @param {string} word
 * @return {number}
 */
var wonderfulSubstrings = function (word) {
    const cnt = new Array(1024).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let st = 0;
    for (const c of word) {
        st ^= 1 << (c.charCodeAt() - 'a'.charCodeAt());
        ans += cnt[st];
        for (let i = 0; i < 10; ++i) {
            ans += cnt[st ^ (1 << i)];
        }
        cnt[st]++;
    }
    return ans;
};
