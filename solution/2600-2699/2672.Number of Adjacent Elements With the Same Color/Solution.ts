function colorTheArray(n: number, queries: number[][]): number[] {
    const nums: number[] = new Array(n).fill(0);
    const ans: number[] = [];
    let x = 0;
    for (const [i, c] of queries) {
        if (i > 0 && nums[i] > 0 && nums[i - 1] == nums[i]) {
            --x;
        }
        if (i < n - 1 && nums[i] > 0 && nums[i + 1] == nums[i]) {
            --x;
        }
        if (i > 0 && nums[i - 1] == c) {
            ++x;
        }
        if (i < n - 1 && nums[i + 1] == c) {
            ++x;
        }
        ans.push(x);
        nums[i] = c;
    }
    return ans;
}
