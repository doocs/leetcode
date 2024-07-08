function findTheWinner(n: number, k: number): number {
    if (n === 1) return 1;
    const res = (findTheWinner(n - 1, k) + k) % n;
    return res ? res : n;
}
