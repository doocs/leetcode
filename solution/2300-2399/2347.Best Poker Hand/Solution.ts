function bestHand(ranks: number[], suits: string[]): string {
    const s = new Set<string>();
    for (const c of suits) {
        s.add(c);
    }
    if (s.size == 1) {
        return 'Flush';
    }
    const cnt = new Array(14).fill(0);
    let pair = false;
    for (const x of ranks) {
        if (++cnt[x] == 3) {
            return 'Three of a Kind';
        }
        pair = pair || cnt[x] == 2;
    }
    return pair ? 'Pair' : 'High Card';
}
