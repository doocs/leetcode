function kItemsWithMaximumSum(
    numOnes: number,
    numZeros: number,
    numNegOnes: number,
    k: number,
): number {
    if (numOnes >= k) {
        return k;
    }
    k -= numOnes;
    if (numZeros >= k) {
        return numOnes;
    }
    k -= numZeros;
    return numOnes - k;
}
