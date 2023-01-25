/**
 * @param {number[][]} points
 * @return {number}
 */
var maxWidthOfVerticalArea = function (points) {
    points.sort((a, b) => a[0] - b[0]);
    let ans = 0;
    let px = points[0][0];
    for (const [x, _] of points) {
        ans = Math.max(ans, x - px);
        px = x;
    }
    return ans;
};
