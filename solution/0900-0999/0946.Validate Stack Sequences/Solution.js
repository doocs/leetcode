/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function (pushed, popped) {
    let stk = [];
    let j = 0;
    for (const v of pushed) {
        stk.push(v);
        while (stk.length && stk[stk.length - 1] == popped[j]) {
            stk.pop();
            ++j;
        }
    }
    return j == pushed.length;
};
