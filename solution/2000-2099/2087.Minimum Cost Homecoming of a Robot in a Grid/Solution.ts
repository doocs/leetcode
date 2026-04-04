function minCost(
    startPos: number[],
    homePos: number[],
    rowCosts: number[],
    colCosts: number[]
): number {
    const calc = (nums: number[], i: number, j: number): number => {
        let res = 0;
        for (let k = i; k <= j; ++k) {
            res += nums[k];
        }
        return res;
    };

    const [x0, y0] = startPos;
    const [x1, y1] = homePos;

    const dx = x0 < x1
        ? calc(rowCosts, x0 + 1, x1)
        : calc(rowCosts, x1, x0 - 1);

    const dy = y0 < y1
        ? calc(colCosts, y0 + 1, y1)
        : calc(colCosts, y1, y0 - 1);

    return dx + dy;
}
