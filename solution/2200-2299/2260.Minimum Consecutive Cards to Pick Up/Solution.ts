function minimumCardPickup(cards: number[]): number {
    const n = cards.length;
    let hashMap = new Map<number, number>();
    const max = Number.MAX_SAFE_INTEGER;
    let ans = max;
    for (let i = 0; i < n; i++) {
        let num = cards[i];
        if (hashMap.has(num)) {
            ans = Math.min(i - hashMap.get(num) + 1, ans);
        }
        hashMap.set(num, i);
    }
    return ans == max ? -1 : ans;
}
