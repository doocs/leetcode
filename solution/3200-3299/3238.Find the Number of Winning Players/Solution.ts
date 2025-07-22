function winningPlayerCount(n: number, pick: number[][]): number {
    const cnt: number[][] = Array.from({ length: n }, () => Array(11).fill(0));
    const s = new Set<number>();
    for (const [x, y] of pick) {
        if (++cnt[x][y] > x) {
            s.add(x);
        }
    }
    return s.size;
}
