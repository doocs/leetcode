/**
 * @param {number[][]} wall
 * @return {number}
 */
var leastBricks = function (wall) {
    const cnt = new Map();
    for (const row of wall) {
        let width = 0;
        for (let i = 0, n = row.length - 1; i < n; ++i) {
            width += row[i];
            cnt.set(width, (cnt.get(width) || 0) + 1);
        }
    }
    let max = 0;
    for (const v of cnt.values()) {
        max = Math.max(max, v);
    }
    return wall.length - max;
};
