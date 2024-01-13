function checkIfPrerequisite(n: number, prerequisites: number[][], queries: number[][]): boolean[] {
    const f = Array.from({ length: n }, () => Array(n).fill(false));
    prerequisites.forEach(([a, b]) => (f[a][b] = true));
    for (let k = 0; k < n; ++k) {
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                f[i][j] ||= f[i][k] && f[k][j];
            }
        }
    }
    return queries.map(([a, b]) => f[a][b]);
}
