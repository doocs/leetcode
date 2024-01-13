function canMakeArithmeticProgression(arr: number[]): boolean {
    const n = arr.length;
    const map = new Map<number, number>();
    let min = Infinity;
    let max = -Infinity;
    for (const num of arr) {
        map.set(num, (map.get(num) ?? 0) + 1);
        min = Math.min(min, num);
        max = Math.max(max, num);
    }
    if (max === min) {
        return true;
    }
    if ((max - min) % (arr.length - 1)) {
        return false;
    }
    const diff = (max - min) / (arr.length - 1);
    for (let i = min; i <= max; i += diff) {
        if (map.get(i) !== 1) {
            return false;
        }
    }
    return true;
}
