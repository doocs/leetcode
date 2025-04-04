function isNStraightHand(hand: number[], groupSize: number): boolean {
    if (hand.length % groupSize !== 0) {
        return false;
    }
    const cnt = new Map<number, number>();
    for (const x of hand) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    hand.sort((a, b) => a - b);
    for (const x of hand) {
        if (cnt.get(x)! > 0) {
            for (let y = x; y < x + groupSize; y++) {
                if ((cnt.get(y) || 0) === 0) {
                    return false;
                }
                cnt.set(y, cnt.get(y)! - 1);
            }
        }
    }
    return true;
}
