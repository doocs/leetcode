function sumEvenAfterQueries(nums: number[], queries: number[][]): number[] {
    let s = nums.reduce((acc, x) => acc + (x % 2 === 0 ? x : 0), 0);
    const ans: number[] = [];
    for (const [v, i] of queries) {
        if (nums[i] % 2 === 0) {
            s -= nums[i];
        }
        nums[i] += v;
        if (nums[i] % 2 === 0) {
            s += nums[i];
        }
        ans.push(s);
    }
    return ans;
}
