function countGoodRectangles(rectangles: number[][]): number {
    let maxLen = 0,
        ans = 0;
    for (let [l, w] of rectangles) {
        let k = Math.min(l, w);
        if (k == maxLen) {
            ans++;
        } else if (k > maxLen) {
            maxLen = k;
            ans = 1;
        }
    }
    return ans;
}
