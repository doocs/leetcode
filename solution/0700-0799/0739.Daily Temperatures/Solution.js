/**
 * @param {number[]} temperatures
 * @return {number[]}
 */
var dailyTemperatures = function (temperatures) {
    const n = temperatures.length;
    const ans = Array(n).fill(0);
    const stk = [];
    for (let i = n - 1; ~i; --i) {
        while (stk.length && temperatures[stk.at(-1)] <= temperatures[i]) {
            stk.pop();
        }
        if (stk.length) {
            ans[i] = stk.at(-1) - i;
        }
        stk.push(i);
    }
    return ans;
};
