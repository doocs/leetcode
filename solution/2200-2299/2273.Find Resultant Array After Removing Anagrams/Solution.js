/**
 * @param {string[]} words
 * @return {string[]}
 */
var removeAnagrams = function (words) {
    const ans = [words[0]];
    const check = (s, t) => {
        if (s.length !== t.length) {
            return true;
        }
        const cnt = Array(26).fill(0);
        for (const c of s) {
            ++cnt[c.charCodeAt() - 97];
        }
        for (const c of t) {
            if (--cnt[c.charCodeAt() - 97] < 0) {
                return true;
            }
        }
        return false;
    };
    for (let i = 1; i < words.length; ++i) {
        if (check(words[i - 1], words[i])) {
            ans.push(words[i]);
        }
    }
    return ans;
};
