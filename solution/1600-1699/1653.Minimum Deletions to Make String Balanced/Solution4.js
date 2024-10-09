/**
 * @param {string} s
 * @return {number}
 */
var minimumDeletions = function (s) {
    const stk = [];
    let res = 0;

    for (const ch of s) {
        if (stk.at(-1) === 'b' && ch === 'a') {
            stk.pop();
            res++;
        } else stk.push(ch);
    }

    return res;
};
