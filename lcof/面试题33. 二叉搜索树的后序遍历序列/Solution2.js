/**
 * @param {number[]} postorder
 * @return {boolean}
 */
var verifyPostorder = function (postorder) {
    let mx = 1 << 30;
    const stk = [];
    for (let i = postorder.length - 1; i >= 0; --i) {
        const x = postorder[i];
        if (x > mx) {
            return false;
        }
        while (stk.length && stk[stk.length - 1] > x) {
            mx = stk.pop();
        }
        stk.push(x);
    }
    return true;
};
