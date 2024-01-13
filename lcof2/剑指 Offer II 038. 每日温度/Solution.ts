function dailyTemperatures(temperatures: number[]): number[] {
    const n = temperatures.length;
    const res = new Array(n);
    const stack = [];
    for (let i = n - 1; i >= 0; i--) {
        while (stack.length !== 0 && temperatures[stack[stack.length - 1]] <= temperatures[i]) {
            stack.pop();
        }
        res[i] = stack.length === 0 ? 0 : stack[stack.length - 1] - i;
        stack.push(i);
    }
    return res;
}
