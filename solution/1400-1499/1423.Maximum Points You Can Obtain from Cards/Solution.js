/**
 * @param {number[]} cardPoints
 * @param {number} k
 * @return {number}
 */
var maxScore = function (cardPoints, k) {
    const n = cardPoints.length;
    let s = cardPoints.slice(-k).reduce((a, b) => a + b);
    let ans = s;
    for (let i = 0; i < k; ++i) {
        s += cardPoints[i] - cardPoints[n - k + i];
        ans = Math.max(ans, s);
    }
    return ans;
};
