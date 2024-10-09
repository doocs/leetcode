function destCity(paths: string[][]): string {
    const s = new Set<string>(paths.map(([a, _]) => a));
    return paths.find(([_, b]) => !s.has(b))![1];
}
