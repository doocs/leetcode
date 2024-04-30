function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const cnt: Map<number, number> = new Map();
    let j = 0;
    for (const x of fruits) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
        if (cnt.size > 2) {
            cnt.set(fruits[j], cnt.get(fruits[j])! - 1);
            if (!cnt.get(fruits[j])) {
                cnt.delete(fruits[j]);
            }
            ++j;
        }
    }
    return n - j;
}
