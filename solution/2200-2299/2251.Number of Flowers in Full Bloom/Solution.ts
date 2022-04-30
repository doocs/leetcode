function fullBloomFlowers(flowers: number[][], persons: number[]): number[] {
    // 离散差分
    let hashMap = new Map();
    for (let [start, end] of flowers) {
        end++;
        hashMap.set(start, (hashMap.get(start) || 0) + 1);
        hashMap.set(end, (hashMap.get(end) || 0) - 1);
    }
    for (let p of persons) {
        if (!hashMap.has(p)) {
            hashMap.set(p, 0);
        }
    }
    let keys = Array.from(hashMap.keys()).sort((a, b) => a - b);
    let pre = 0;
    for (let k of keys) {
        pre += hashMap.get(k);
        hashMap.set(k, pre);
    }
    // 离散查询
    let ans = persons.map(v => hashMap.get(v));
    return ans;
}
