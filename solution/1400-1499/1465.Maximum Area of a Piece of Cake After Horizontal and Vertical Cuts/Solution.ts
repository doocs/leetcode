function maxArea(h: number, w: number, horizontalCuts: number[], verticalCuts: number[]): number {
    const mod = 1e9 + 7;
    horizontalCuts.push(0, h);
    verticalCuts.push(0, w);
    horizontalCuts.sort((a, b) => a - b);
    verticalCuts.sort((a, b) => a - b);
    let [x, y] = [0, 0];
    for (let i = 1; i < horizontalCuts.length; i++) {
        x = Math.max(x, horizontalCuts[i] - horizontalCuts[i - 1]);
    }
    for (let i = 1; i < verticalCuts.length; i++) {
        y = Math.max(y, verticalCuts[i] - verticalCuts[i - 1]);
    }
    return Number((BigInt(x) * BigInt(y)) % BigInt(mod));
}
