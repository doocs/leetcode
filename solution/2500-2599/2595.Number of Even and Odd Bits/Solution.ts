function evenOddBit(n: number): number[] {
    const ans = Array(2).fill(0);
    for (let i = 0; n > 0; n >>= 1, i ^= 1) {
        ans[i] += n & 1;
    }
    return ans;
}
