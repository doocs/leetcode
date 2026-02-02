function goodIndices(s: string): number[] {
    const ans: number[] = [];
    for (let i = 0; i < s.length; i++) {
        const t = String(i);
        const k = t.length;
        if (s.slice(i + 1 - k, i + 1) === t) {
            ans.push(i);
        }
    }
    return ans;
}
