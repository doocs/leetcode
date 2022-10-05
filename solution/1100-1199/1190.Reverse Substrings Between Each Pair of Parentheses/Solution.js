/**
 * @param {string} s
 * @return {string}
 */
var reverseParentheses = function (s) {
    const n = s.length;
    const d = new Array(n).fill(0);
    const stk = [];
    for (let i = 0; i < n; ++i) {
        if (s[i] == '(') {
            stk.push(i);
        } else if (s[i] == ')') {
            const j = stk.pop();
            d[i] = j;
            d[j] = i;
        }
    }
    let i = 0;
    let x = 1;
    const ans = [];
    while (i < n) {
        const c = s.charAt(i);
        if (c == '(' || c == ')') {
            i = d[i];
            x = -x;
        } else {
            ans.push(c);
        }
        i += x;
    }
    return ans.join('');
};
