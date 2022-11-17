function countOdds(low: number, high: number): number {
    return ((high + 1) >> 1) - (low >> 1);
}
