function occurrencesOfElement(nums: number[], queries: number[], x: number): number[] {
    const ids: number[] = nums.map((v, i) => (v === x ? i : -1)).filter(v => v !== -1);
    return queries.map(i => ids[i - 1] ?? -1);
}
