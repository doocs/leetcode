function dailyTemperatures(temperatures: number[]): number[] {
    const n = temperatures.length;
    const res = new Array(n).fill(0);
    const stack = [];
    for (let i = 0; i < n; i++) {
        const temperature = temperatures[i];
        while (
            stack.length !== 0 &&
            temperatures[stack[stack.length - 1]] < temperature
        ) {
            const j = stack.pop();
            res[j] = i - j;
        }
        stack.push(i);
    }
    return res;
}
