/**
 * @param {string} s
 * @param {number} count
 * @return {number}
 */
var equalCountSubstrings = function (s, count) {
    let ans = 0;
    const n = s.length;
    for (let x = 1; x <= 26 && x * count <= n; ++x) {
        const m = x * count;
        const cnt = new Array(26).fill(0);
        let y = 0;
        for (let i = 0; i < n; ++i) {
            const a = s.charCodeAt(i) - 'a'.charCodeAt(0);
            ++cnt[a];
            y += cnt[a] == count;
            y -= cnt[a] == count + 1;
            const j = i - m;
            if (j >= 0) {
                const b = s.charCodeAt(j) - 'a'.charCodeAt(0);
                --cnt[b];
                y += cnt[b] == count;
                y -= cnt[b] == count - 1;
            }
            ans += x == y;
        }
    }
    return ans;
};
