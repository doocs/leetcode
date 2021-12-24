function minDifference(nums: number[], queries: number[][]): number[] {
    let m = nums.length,
        n = queries.length;
    let max = 100;
    // let max = Math.max(...nums);
    let pre: number[][] = [];
    pre.push(new Array(max + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        let num = nums[i];
        pre.push(pre[i].slice());
        pre[i + 1][num] += 1;
    }

    let ans = [];
    for (let [left, right] of queries) {
        let last = -1;
        let min = Infinity;
        for (let j = 1; j < max + 1; ++j) {
            if (pre[left][j] < pre[right + 1][j]) {
                if (last != -1) {
                    min = Math.min(min, j - last);
                }
                last = j;
            }
        }
        ans.push(min == Infinity ? -1 : min);
    }
    return ans;
}
