/**
 * @param {number[][]} intervals
 * @return {number[][]}
 */
var merge = function (intervals) {
    intervals.sort((a, b) => a[0] - b[0]);
    const result = [];
    const n = intervals.length;
    let i = 0;
    while (i < n) {
        const left = intervals[i][0];
        let right = intervals[i][1];
        while (true) {
            i++;
            if (i < n && right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
            } else {
                result.push([left, right]);
                break;
            }
        }
    }
    return result;
};
