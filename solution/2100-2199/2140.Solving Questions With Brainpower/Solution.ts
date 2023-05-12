function mostPoints(questions: number[][]): number {
    const n = questions.length;
    const f = Array(n).fill(0);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] > 0) {
            return f[i];
        }
        const [p, b] = questions[i];
        return (f[i] = Math.max(p + dfs(i + b + 1), dfs(i + 1)));
    };
    return dfs(0);
}
