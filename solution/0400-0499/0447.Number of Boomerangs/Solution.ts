function numberOfBoomerangs(points: number[][]): number {
    let ans = 0;
    for (let p1 of points) {
        let hashMap: Map<number, number> = new Map();
        for (let p2 of points) {
            const distance = (p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2;
            hashMap.set(distance, (hashMap.get(distance) || 0) + 1);
        }
        for (let [, v] of [...hashMap]) {
            ans += v * (v - 1);
        }
    }
    return ans;
}
