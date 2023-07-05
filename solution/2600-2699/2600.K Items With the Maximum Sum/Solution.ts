function kItemsWithMaximumSum(
    numOnes: number,
    numZeros: number,
    numNegOnes: number,
    k: number,
): number {
    if (numOnes >= k) {
        return k;
    }
    if (numZeros >= k - numOnes) {
        return numOnes;
    }
    return numOnes - (k - numOnes - numZeros);
}
