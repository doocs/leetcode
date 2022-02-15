function maxScoreIndices(nums: number[]): number[] {
    const n = nums.length;
    const total = nums.reduce((a, c) => a + c, 0);
    let left = 0,
        right = total;
    let record: Array<number> = [total];
    for (const num of nums) {
        if (num == 0) {
            left++;
        } else {
            right--;
        }
        record.push(left + right);
    }
    const max = Math.max(...record);
    let ans: Array<number> = [];
    for (let i = 0; i <= n; i++) {
        if (record[i] == max) {
            ans.push(i);
        }
    }
    return ans;
}
