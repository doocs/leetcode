function findTheWinner(n: number, k: number): number {
    if (n === 1) {
        return 1;
    }
    const ans = (k + findTheWinner(n - 1, k)) % n;
    return ans ? ans : n;
}
