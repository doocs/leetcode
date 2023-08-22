function minimumOneBitOperations(n: number): number {
    let ans = 0;
    for (; n > 0; n >>= 1) {
        ans ^= n;
    }
    return ans;
}
