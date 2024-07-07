/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function (digits) {
    if (digits.length === 0) {
        return [];
    }
    const ans = [];
    const n = digits.length;
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'].map(x => [...x]);

    const dfs = (curr, start) => {
        if (curr.length === n) {
            ans.push(curr);
            return;
        }
        for (let i = start; i < n; i++) {
            for (const ch of map[+digits[i] - 2]) {
                dfs(curr + ch, i + 1);
            }
        }
    };
    dfs('', 0);
    return ans;
};
