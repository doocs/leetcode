/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function (pushed, popped) {
    const stk = [];
    let i = 0;
    for (const x of pushed) {
        stk.push(x);
        while (stk.length && stk.at(-1) === popped[i]) {
            stk.pop();
            i++;
        }
    }
    return i === popped.length;
};
