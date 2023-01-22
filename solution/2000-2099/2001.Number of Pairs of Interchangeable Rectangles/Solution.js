/**
 * @param {number[][]} rectangles
 * @return {number}
 */
var interchangeableRectangles = function (rectangles) {
    const cnt = new Map();
    let ans = 0;
    for (let [w, h] of rectangles) {
        const g = gcd(w, h);
        w = Math.floor(w / g);
        h = Math.floor(h / g);
        const x = w * (rectangles.length + 1) + h;
        ans += cnt.get(x) | 0;
        cnt.set(x, (cnt.get(x) | 0) + 1);
    }
    return ans;
};

function gcd(a, b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
