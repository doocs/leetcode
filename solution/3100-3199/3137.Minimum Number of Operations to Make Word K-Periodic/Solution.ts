function minimumOperationsToMakeKPeriodic(word: string, k: number): number {
    const cnt: Map<string, number> = new Map();
    const n: number = word.length;
    let mx: number = 0;
    for (let i = 0; i < n; i += k) {
        const s = word.slice(i, i + k);
        cnt.set(s, (cnt.get(s) || 0) + 1);
        mx = Math.max(mx, cnt.get(s)!);
    }
    return Math.floor(n / k) - mx;
}
