function bitwiseComplement(n: number): number {
    if (n === 0) {
        return 1;
    }
    let ans = 0;
    for (let i = 0; n; n >>= 1) {
        ans |= ((n & 1) ^ 1) << i++;
    }
    return ans;
}
