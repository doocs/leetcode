function canSeePersonsCount(heights: number[]): number[] {
    const n = heights.length;
    const ans: number[] = new Array(n).fill(0);
    const stk: number[] = [];
    for (let i = n - 1; ~i; --i) {
        while (stk.length && stk.at(-1) < heights[i]) {
            ++ans[i];
            stk.pop();
        }
        if (stk.length) {
            ++ans[i];
        }
        stk.push(heights[i]);
    }
    return ans;
}
