function unmarkedSumArray(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    let s = nums.reduce((acc, x) => acc + x, 0);
    const mark: boolean[] = Array(n).fill(false);
    const arr = nums.map((x, i) => [x, i]);
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    let j = 0;
    const ans: number[] = [];
    for (let [index, k] of queries) {
        if (!mark[index]) {
            mark[index] = true;
            s -= nums[index];
        }
        for (; k && j < n; ++j) {
            if (!mark[arr[j][1]]) {
                mark[arr[j][1]] = true;
                s -= arr[j][0];
                --k;
            }
        }
        ans.push(s);
    }
    return ans;
}
