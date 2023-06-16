function numberOfCuts(n: number): number {
    return n > 1 && n & 1 ? n : n >> 1;
}
