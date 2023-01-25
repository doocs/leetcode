function sortTheStudents(score: number[][], k: number): number[][] {
    return score.sort((a, b) => b[k] - a[k]);
}
