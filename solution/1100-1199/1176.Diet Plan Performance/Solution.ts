function dietPlanPerformance(calories: number[], k: number, lower: number, upper: number): number {
    const n = calories.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + calories[i];
    }
    let ans = 0;
    for (let i = 0; i < n - k + 1; ++i) {
        const t = s[i + k] - s[i];
        if (t < lower) {
            --ans;
        } else if (t > upper) {
            ++ans;
        }
    }
    return ans;
}
