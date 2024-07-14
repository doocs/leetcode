function numberOfSubarrays(nums: number[]): number {
    const stk: number[][] = [];
    let ans = 0;
    for (const x of nums) {
        while (stk.length > 0 && stk.at(-1)![0] < x) {
            stk.pop();
        }
        if (stk.length === 0 || stk.at(-1)![0] > x) {
            stk.push([x, 1]);
        } else {
            stk.at(-1)![1]++;
        }
        ans += stk.at(-1)![1];
    }
    return ans;
}
