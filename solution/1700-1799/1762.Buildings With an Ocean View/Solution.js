/**
 * @param {number[]} heights
 * @return {number[]}
 */
var findBuildings = function (heights) {
    const ans = [];
    let mx = 0;
    for (let i = heights.length - 1; ~i; --i) {
        if (heights[i] > mx) {
            ans.push(i);
            mx = heights[i];
        }
    }
    return ans.reverse();
};
