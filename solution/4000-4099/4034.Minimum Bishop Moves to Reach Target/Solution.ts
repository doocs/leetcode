function minBishopMoves(source: number[], target: number[]): number {
    const [sr, sc] = source;
    const [tr, tc] = target;
    if ((sr + sc) % 2 !== (tr + tc) % 2) {
        return -1;
    }
    if (Math.abs(sr - tr) === Math.abs(sc - tc)) {
        return 1;
    }
    return 2;
}
