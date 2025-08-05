function sortThreats(threats: number[][]): number[][] {
    threats.sort((a, b) => {
        const score1 = 2 * a[1] + a[2];
        const score2 = 2 * b[1] + b[2];
        if (score1 === score2) {
            return a[0] - b[0];
        }
        return score2 - score1;
    });
    return threats;
}
