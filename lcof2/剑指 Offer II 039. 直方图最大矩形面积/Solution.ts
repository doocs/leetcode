function largestRectangleArea(heights: number[]): number {
    const n = heights.length;
    const left: number[] = new Array(n).fill(-1);
    const right: number[] = new Array(n).fill(n);
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length && heights[stk[stk.length - 1]] >= heights[i]) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && heights[stk[stk.length - 1]] >= heights[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
    }
    return ans;
}
