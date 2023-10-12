function seePeople(heights: number[][]): number[][] {
    const f = (nums: number[]): number[] => {
        const n = nums.length;
        const ans: number[] = new Array(n).fill(0);
        const stk: number[] = [];
        for (let i = n - 1; ~i; --i) {
            while (stk.length && stk.at(-1) < nums[i]) {
                stk.pop();
                ++ans[i];
            }
            if (stk.length) {
                ++ans[i];
            }
            while (stk.length && stk.at(-1) === nums[i]) {
                stk.pop();
            }
            stk.push(nums[i]);
        }
        return ans;
    };
    const ans: number[][] = [];
    for (const row of heights) {
        ans.push(f(row));
    }
    const n = heights[0].length;
    for (let j = 0; j < n; ++j) {
        const col: number[] = [];
        for (const row of heights) {
            col.push(row[j]);
        }
        const add = f(col);
        for (let i = 0; i < ans.length; ++i) {
            ans[i][j] += add[i];
        }
    }
    return ans;
}
