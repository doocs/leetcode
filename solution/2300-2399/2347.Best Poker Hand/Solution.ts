function bestHand(ranks: number[], suits: string[]): string {
    let flush = true;
    for (let i = 1; i < 5 && flush; ++i) {
        flush = suits[i] == suits[i - 1];
    }
    if (flush) {
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
