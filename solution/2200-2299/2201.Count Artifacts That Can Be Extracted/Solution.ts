function digArtifacts(
    n: number,
    artifacts: number[][],
    dig: number[][],
): number {
    let visited = Array.from({ length: n }, v => new Array(n).fill(false));
    for (let [i, j] of dig) {
        visited[i][j] = true;
    }
    let ans = 0;
    for (let [a, b, c, d] of artifacts) {
        let flag = true;
        for (let i = a; i <= c && flag; i++) {
            for (let j = b; j <= d && flag; j++) {
                if (!visited[i][j]) {
                    flag = false;
                }
            }
        }
        flag && ans++;
    }
    return ans;
}
