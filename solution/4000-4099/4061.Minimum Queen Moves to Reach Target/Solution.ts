function minQueenMoves(source: number[], target: number[]): number {
    const [sr, sc] = source;
    const [tr, tc] = target;
    if (sr === tr && sc === tc) {
        return 0;
    }
    if (sr === tr || sc === tc || Math.abs(sr - tr) === Math.abs(sc - tc)) {
        return 1;
    }
    return 2;
}
