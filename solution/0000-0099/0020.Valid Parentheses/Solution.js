/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function (s) {
    const d = new Map([
        ['(', ')'],
        ['[', ']'],
        ['{', '}'],
    ]);
    const stk = [];
    for (const c of s) {
        if (d.has(c)) {
            stk.push(d.get(c));
        } else if (stk.pop() !== c) {
            return false;
        }
    }
    return stk.length === 0;
};
