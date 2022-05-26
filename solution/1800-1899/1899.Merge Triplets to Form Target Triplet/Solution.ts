function mergeTriplets(triplets: number[][], target: number[]): boolean {
    let [x, y, z] = target; // 目标值
    let [i, j, k] = [0, 0, 0]; // 最大值
    for (let triplet of triplets) {
        let [a, b, c] = triplet; // 当前值
        if (a <= x && b <= y && c <= z) {
            i = Math.max(i, a);
            j = Math.max(j, b);
            k = Math.max(k, c);
        }
    }
    return i == x && j == y && k == z;
}
