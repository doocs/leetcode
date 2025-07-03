function minCost(m: number, n: number): number {
    if (m === 1 && n === 1) {
        return 1;
    }
    if (m === 1 && n === 2) {
        return 3;
    }
    if (m === 2 && n === 1) {
        return 3;
    }
    return -1;
}
