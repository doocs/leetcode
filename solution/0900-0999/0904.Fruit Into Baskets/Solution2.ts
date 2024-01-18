function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const map = new Map<number, number>();
    let i = 0;
    for (const fruit of fruits) {
        map.set(fruit, (map.get(fruit) ?? 0) + 1);
        if (map.size > 2) {
            const k = fruits[i++];
            map.set(k, map.get(k) - 1);
            if (map.get(k) == 0) {
                map.delete(k);
            }
        }
    }
    return n - i;
}
