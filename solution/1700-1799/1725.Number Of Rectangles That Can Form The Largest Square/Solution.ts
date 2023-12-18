function countGoodRectangles(rectangles: number[][]): number {
    let [ans, mx] = [0, 0];
    for (const [l, w] of rectangles) {
        const x = Math.min(l, w);
        if (mx < x) {
            mx = x;
            ans = 1;
        } else if (mx === x) {
            ++ans;
        }
    }
    return ans;
}
