function maximumImportance(n: number, roads: number[][]): number {
    const deg: number[] = Array(n).fill(0);
    for (const [a, b] of roads) {
        ++deg[a];
        ++deg[b];
    }
    deg.sort((a, b) => a - b);
    return deg.reduce((acc, cur, idx) => acc + (idx + 1) * cur, 0);
}
