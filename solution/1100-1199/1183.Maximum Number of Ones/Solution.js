/**
 * @param {number} width
 * @param {number} height
 * @param {number} sideLength
 * @param {number} maxOnes
 * @return {number}
 */
var maximumNumberOfOnes = function (width, height, sideLength, maxOnes) {
    const x = sideLength;
    const cnt = new Array(x * x).fill(0);
    for (let i = 0; i < width; ++i) {
        for (let j = 0; j < height; ++j) {
            const k = (i % x) * x + (j % x);
            ++cnt[k];
        }
    }
    cnt.sort((a, b) => b - a);
    let ans = 0;
    for (let i = 0; i < maxOnes; ++i) {
        ans += cnt[i];
    }
    return ans;
};
