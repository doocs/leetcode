function isNStraightHand(hand: number[], groupSize: number): boolean {
    const n = hand.length;
    if (n % groupSize) {
        return false;
    }

    const groups: number[][] = Array.from({ length: n / groupSize }, () => []);
    hand.sort((a, b) => a - b);

    for (let i = 0; i < n; i++) {
        let isPushed = false;

        for (const g of groups) {
            if (g.length === groupSize || (g.length && hand[i] - g.at(-1)! !== 1)) {
                continue;
            }

            g.push(hand[i]);
            isPushed = true;
            break;
        }

        if (!isPushed) {
            return false;
        }
    }

    return true;
}
