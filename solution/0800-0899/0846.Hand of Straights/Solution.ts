function isNStraightHand(hand: number[], groupSize: number) {
    const cnt: Record<number, number> = {};
    for (const i of hand) {
        cnt[i] = (cnt[i] ?? 0) + 1;
    }

    const keys = Object.keys(cnt).map(Number);
    for (const i of keys) {
        while (cnt[i]) {
            for (let j = i; j < groupSize + i; j++) {
                if (!cnt[j]) {
                    return false;
                }
                cnt[j]--;
            }
        }
    }

    return true;
}
