function findClosest(x: number, y: number, z: number): number {
    const a = Math.abs(x - z);
    const b = Math.abs(y - z);
    return a === b ? 0 : a < b ? 1 : 2;
}
