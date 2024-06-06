export function isNStraightHand(hand: number[], groupSize: number) {
    const map: Record<number, number> = {};

    for (const i of hand) {
        map[i] = (map[i] ?? 0) + 1;
    }

    const keys = Object.keys(map).map(Number);

    for (const i of keys) {
        while (map[i]) {
            for (let j = i; j < groupSize + i; j++) {
                if (!map[j]) return false;
                map[j]--;
            }
        }
    }

    return true;
}
