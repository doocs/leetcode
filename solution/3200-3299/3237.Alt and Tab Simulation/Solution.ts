function simulationResult(windows: number[], queries: number[]): number[] {
    const n = windows.length;
    const s: boolean[] = Array(n + 1).fill(false);
    const ans: number[] = [];
    for (let i = queries.length - 1; i >= 0; i--) {
        const q = queries[i];
        if (!s[q]) {
            s[q] = true;
            ans.push(q);
        }
    }
    for (const w of windows) {
        if (!s[w]) {
            ans.push(w);
        }
    }
    return ans;
}
