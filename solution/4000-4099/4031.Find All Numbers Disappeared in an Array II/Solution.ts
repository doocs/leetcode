function findDisappearedNumbers(nums: number[], lower: number, upper: number): number[][] {
    nums.sort((a, b) => a - b);
    const ans: number[][] = [];
    let prev = lower - 1;
    for (const x of nums) {
        if (x < lower || x > upper) {
            continue;
        }
        if (x - prev > 1) {
            ans.push([prev + 1, x - 1]);
        }
        prev = x;
    }
    if (prev < upper) {
        ans.push([prev + 1, upper]);
    }
    return ans;
}
