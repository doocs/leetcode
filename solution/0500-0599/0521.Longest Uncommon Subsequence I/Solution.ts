function findLUSlength(a: string, b: string): number {
    return a != b ? Math.max(a.length, b.length) : -1;
}
