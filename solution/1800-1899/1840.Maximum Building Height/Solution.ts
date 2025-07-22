function maxBuilding(n: number, restrictions: number[][]): number {
    restrictions.push([1, 0]);
    restrictions.sort((a, b) => a[0] - b[0]);
    if (restrictions[restrictions.length - 1][0] !== n) {
        restrictions.push([n, n - 1]);
    }

    const m = restrictions.length;
    for (let i = 1; i < m; ++i) {
        restrictions[i][1] = Math.min(
            restrictions[i][1],
            restrictions[i - 1][1] + restrictions[i][0] - restrictions[i - 1][0],
        );
    }

    for (let i = m - 2; i >= 0; --i) {
        restrictions[i][1] = Math.min(
            restrictions[i][1],
            restrictions[i + 1][1] + restrictions[i + 1][0] - restrictions[i][0],
        );
    }

    let ans = 0;
    for (let i = 0; i < m - 1; ++i) {
        const t = Math.floor(
            (restrictions[i][1] +
                restrictions[i + 1][1] +
                restrictions[i + 1][0] -
                restrictions[i][0]) /
                2,
        );
        ans = Math.max(ans, t);
    }

    return ans;
}
