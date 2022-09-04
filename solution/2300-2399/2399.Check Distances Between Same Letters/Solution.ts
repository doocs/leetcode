function checkDistances(s: string, distance: number[]): boolean {
    const n = s.length;
    const d = new Array(26).fill(0);
    for (let i = 0; i < n; i++) {
        const j = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
        if (d[j] > 0 && i - d[j] !== distance[j]) {
            return false;
        }
        d[j] = i + 1;
    }
    return true;
}
