function sortPeople(names: string[], heights: number[]): string[] {
    const n = names.length;
    const idx = new Array(n);
    for (let i = 0; i < n; ++i) {
        idx[i] = i;
    }
    idx.sort((i, j) => heights[j] - heights[i]);
    const ans: string[] = [];
    for (const i of idx) {
        ans.push(names[i]);
    }
    return ans;
}
