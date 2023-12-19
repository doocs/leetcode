/**
 * @param {string} s
 * @return {string}
 */
var sortSentence = function (s) {
    const ws = s.split(' ');
    const ans = Array(ws.length);
    for (const w of ws) {
        ans[w.charCodeAt(w.length - 1) - '1'.charCodeAt(0)] = w.slice(0, -1);
    }
    return ans.join(' ');
};
