function minimumCardPickup(cards: number[]): number {
    const n = cards.length;
    const last = new Map<number, number>();
    let ans = n + 1;
    for (let i = 0; i < n; ++i) {
        if (last.has(cards[i])) {
            ans = Math.min(ans, i - last.get(cards[i]) + 1);
        }
        last.set(cards[i], i);
    }
    return ans > n ? -1 : ans;
}
