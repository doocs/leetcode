function mergeTriplets(triplets: number[][], target: number[]): boolean {
    const [x, y, z] = target;
    let [d, e, f] = [0, 0, 0];
    for (const [a, b, c] of triplets) {
        if (a <= x && b <= y && c <= z) {
            d = Math.max(d, a);
            e = Math.max(e, b);
            f = Math.max(f, c);
        }
    }
    return d === x && e === y && f === z;
}
