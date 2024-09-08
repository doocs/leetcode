function maxPossibleScore(start: number[], d: number): number {
    start.sort((a, b) => a - b);
    let [l, r] = [0, start.at(-1)! + d - start[0]];
    const check = (mi: number): boolean => {
        let last = -Infinity;
        for (const st of start) {
            if (last + mi > st + d) {
                return false;
            }
            last = Math.max(st, last + mi);
        }
        return true;
    };
    while (l < r) {
        const mid = l + ((r - l + 1) >> 1);
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
