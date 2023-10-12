function expectNumber(scores: number[]): number {
    const s: Set<number> = new Set<number>(scores);
    return s.size;
}
