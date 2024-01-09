function areaOfMaxDiagonal(dimensions: number[][]): number {
    let [ans, mx] = [0, 0];
    for (const [l, w] of dimensions) {
        const t = l * l + w * w;
        if (mx < t) {
            mx = t;
            ans = l * w;
        } else if (mx === t) {
            ans = Math.max(ans, l * w);
        }
    }
    return ans;
}
