function answerQueries(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    const m = queries.length;
    const idx: number[] = new Array(m);
    for (let i = 0; i < m; i++) {
        idx[i] = i;
    }
    idx.sort((i, j) => queries[i] - queries[j]);
    const ans: number[] = new Array(m);
    let s = 0;
    let j = 0;
    for (const i of idx) {
        while (j < nums.length && s + nums[j] <= queries[i]) {
            s += nums[j++];
        }
        ans[i] = j;
    }
    return ans;
}
