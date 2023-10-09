function isReachableAtTime(sx: number, sy: number, fx: number, fy: number, t: number): boolean {
    if (sx === fx && sy === fy) {
        return t !== 1;
    }
    const dx = Math.abs(sx - fx);
    const dy = Math.abs(sy - fy);
    return Math.max(dx, dy) <= t;
}
