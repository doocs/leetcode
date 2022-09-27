function sortPeople(names: string[], heights: number[]): string[] {
    return names
        .map<[string, number]>((s, i) => [s, heights[i]])
        .sort((a, b) => b[1] - a[1])
        .map(([v]) => v);
}
