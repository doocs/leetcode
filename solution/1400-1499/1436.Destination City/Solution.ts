function destCity(paths: string[][]): string {
    const set = new Set(paths.map(([a]) => a));
    for (const [_, b] of paths) {
        if (!set.has(b)) {
            return b;
        }
    }
    return '';
}
