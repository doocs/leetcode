function sumEvenAfterQueries(nums: number[], queries: number[][]): number[] {
    let s = 0;
    for (const x of nums) {
        if (x % 2 === 0) {
            s += x;
        }
    }
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
