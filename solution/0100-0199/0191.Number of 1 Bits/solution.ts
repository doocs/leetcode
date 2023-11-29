function hammingWeight(n: number): number {
    let cnt: number = 0;
    while (n !== 0) {
        cnt++;
        n &= (n - 1);
    }
    return cnt;
}
