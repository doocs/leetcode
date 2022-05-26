/**
 * @param {string} s
 * @return {string}
 */
var reverseParentheses = function (s) {
    let stack = [];
    let hashMap = {};
    const n = s.length;
    for (let i = 0; i < n; i++) {
        let cur = s.charAt(i);
        if (cur == '(') {
            stack.push(i);
        } else if (cur == ')') {
            let left = stack.pop();
            hashMap[left] = i;
            hashMap[i] = left;
        }
    }
    let res = [];
    let i = 0;
    let step = 1; // 1向右，-1向左
    while (i > -1 && i < n) {
        let cur = s.charAt(i);
        if (cur == '(' || cur == ')') {
            step = -step;
            i = hashMap[i];
        } else {
            res.push(cur);
        }
        i += step;
    }
    return res.join('');
};
