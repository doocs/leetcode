function findRelativeRanks(score: number[]): string[] {
    const n = score.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((a, b) => score[b] - score[a]);
    const top3 = ['Gold Medal', 'Silver Medal', 'Bronze Medal'];
    const ans: string[] = Array(n);
    for (let i = 0; i < n; i++) {
        if (i < 3) {
            ans[idx[i]] = top3[i];
        } else {
            ans[idx[i]] = (i + 1).toString();
        }
    }
    return ans;
}
