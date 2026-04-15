function closestTarget(words: string[], target: string, startIndex: number): number {
    const n = words.length;
    let ans = n;
    for (let i = 0; i < n; i++) {
        if (words[i] === target) {
            const t = Math.abs(i - startIndex);
            ans = Math.min(ans, t, n - t);
        }
    }
    return ans === n ? -1 : ans;
}
