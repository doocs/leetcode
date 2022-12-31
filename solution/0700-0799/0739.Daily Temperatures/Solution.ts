function dailyTemperatures(temperatures: number[]): number[] {
    const n = temperatures.length;
    const ans = new Array(n).fill(0);
    const stk: number[] = [];
    for (let i = n - 1; i >= 0; --i) {
        while (
            stk.length &&
            temperatures[stk[stk.length - 1]] <= temperatures[i]
        ) {
            stk.pop();
        }
        if (stk.length) {
            ans[i] = stk[stk.length - 1] - i;
        }
        stk.push(i);
    }
    return ans;
}
