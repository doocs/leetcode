function groupThePeople(groupSizes: number[]): number[][] {
    const res = [];
    const map = new Map<number, number[]>();
    const n = groupSizes.length;
    for (let i = 0; i < n; i++) {
        const size = groupSizes[i];
        map.set(size, [...(map.get(size) ?? []), i]);
        const arr = map.get(size);
        if (arr.length === size) {
            res.push(arr);
            map.set(size, []);
        }
    }
    return res;
}
