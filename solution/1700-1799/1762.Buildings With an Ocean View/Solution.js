/**
 * @param {number[]} heights
 * @return {number[]}
 */
var findBuildings = function (heights) {
    let mx = 0;
    let ans = [];
    for (let i = heights.length - 1; i >= 0; --i) {
        const v = heights[i];
        if (mx < v) {
            ans.push(i);
            mx = v;
        }
    }
    return ans.reverse();
};
