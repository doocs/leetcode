/**
 * @param {number} target
 * @return {number[][]}
 */
var findContinuousSequence = function (target) {
    const ans = [];
    let l = 1;
    let r = 2;
    while (l < r) {
        const s = ((l + r) * (r - l + 1)) >> 1;
        if (s == target) {
            const t = [];
            for (let i = l; i <= r; ++i) {
                t.push(i);
            }
            ans.push(t);
            ++l;
        } else if (s < target) {
            ++r;
        } else {
            ++l;
        }
    }
    return ans;
};
