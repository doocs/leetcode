function canSeePersonsCount(heights: number[]): number[] {
    const n = heights.length;
    const ans = new Array(n).fill(0);
    const stack = [];
    for (let i = n - 1; i >= 0; i--) {
        while (stack.length !== 0) {
            ans[i]++;
            if (heights[i] <= heights[stack[stack.length - 1]]) {
                break;
            }
            stack.pop();
        }
        stack.push(i);
    }
    return ans;
}
