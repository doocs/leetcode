function findLUSlength(a: string, b: string): number {
    return a === b ? -1 : Math.max(a.length, b.length);
}
