/**
 * @param {number[]} postorder
 * @return {boolean}
 */
var verifyPostorder = function (postorder) {
    const stk = [[0, postorder.length - 1]];
    while (stk.length) {
        const [l, r] = stk.pop();
        if (l >= r) {
            continue;
        }
        const v = postorder[r];
        let i = l;
        while (i < r && postorder[i] < v) {
            ++i;
        }
        for (let j = i; j < r; ++j) {
            if (postorder[j] < v) {
                return false;
            }
        }
        stk.push([i, r - 1]);
        stk.push([l, i - 1]);
    }
    return true;
};
