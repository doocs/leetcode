function bestHand(ranks: number[], suits: string[]): string {
    if (suits.every(v => v === suits[0])) {
        return 'Flush';
    }
    const count = new Array(14).fill(0);
    let isPair = false;
    for (const v of ranks) {
        if (++count[v] === 3) {
            return 'Three of a Kind';
        }
        isPair = isPair || count[v] === 2;
    }
    if (isPair) {
        return 'Pair';
    }
    return 'High Card';
}
