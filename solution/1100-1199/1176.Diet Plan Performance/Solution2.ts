function dietPlanPerformance(calories: number[], k: number, lower: number, upper: number): number {
    const n = calories.length;
    let s = calories.slice(0, k).reduce((a, b) => a + b);
    let ans = 0;
    if (s < lower) {
        --ans;
    } else if (s > upper) {
        ++ans;
    }
    for (let i = k; i < n; ++i) {
        s += calories[i] - calories[i - k];
        if (s < lower) {
            --ans;
        } else if (s > upper) {
            ++ans;
        }
    }
    return ans;
}
