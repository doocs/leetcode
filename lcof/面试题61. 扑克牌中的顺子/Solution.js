/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isStraight = function (nums) {
    const vis = new Array(14).fill(false);
    let mi = 20;
    let mx = -1;
    for (const x of nums) {
        if (x == 0) {
            continue;
        }
        if (vis[x]) {
            return false;
        }
        vis[x] = true;
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return mx - mi <= 4;
};
