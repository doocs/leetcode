function minOperations(nums: number[]): number {
    const stk: number[] = [];
    let ans = 0;
    for (const x of nums) {
        while (stk.length > 0 && stk[stk.length - 1] > x) {
            ans++;
            stk.pop();
        }
        if (x !== 0 && (stk.length === 0 || stk[stk.length - 1] !== x)) {
            stk.push(x);
        }
    }
    ans += stk.length;
    return ans;
}
