/**
 * @param {string} s
 * @return {string}
 */
var reverseParentheses = function (s) {
    const stk = [];
    for (const c of s) {
        if (c === ')') {
            const t = [];
            while (stk.at(-1) !== '(') {
                t.push(stk.pop());
            }
            stk.pop();
            stk.push(...t);
        } else {
            stk.push(c);
        }
    }
    return stk.join('');
};
