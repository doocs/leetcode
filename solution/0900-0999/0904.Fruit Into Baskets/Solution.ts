function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const map = new Map<number, number>();
    let res = 0;
    let left = 0;
    let right = 0;
    while (right < n) {
        map.set(fruits[right], (map.get(fruits[right]) ?? 0) + 1);
        right++;
        while (map.size > 2) {
            const k = fruits[left++];
            map.set(k, map.get(k) - 1);
            if (map.get(k) === 0) {
                map.delete(k);
            }
        }
        res = Math.max(res, right - left);
    }
    return res;
}
