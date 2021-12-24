/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function (pushed, popped) {
    let s = [];
    let q = 0;
    for (let num of pushed) {
        s.push(num);
        while (s.length > 0 && s[s.length - 1] == popped[q]) {
            ++q;
            s.pop();
        }
    }
    return s.length == 0;
};
