function findTheWinner(n, k) {
    if (n === 1) return 1;
    const res = (findTheWinner(n - 1, k) + k) % n;
    return res ? res : n;
}
