function checkDistances(s: string, distance: number[]): boolean {
    const d: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 1; i <= n; ++i) {
        const j = s.charCodeAt(i - 1) - 97;
        if (d[j] && i - d[j] - 1 !== distance[j]) {
            return false;
        }
        d[j] = i;
    }
    return true;
}
