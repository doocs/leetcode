function recoverOrder(order: number[], friends: number[]): number[] {
    const n = order.length;
    const d: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        d[order[i]] = i;
    }
    return friends.sort((a, b) => d[a] - d[b]);
}
