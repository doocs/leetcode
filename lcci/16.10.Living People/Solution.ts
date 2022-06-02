function maxAliveYear(birth: number[], death: number[]): number {
    const n = birth.length;
    const counter = new Map<number, number>();
    for (let i = 0; i < n; i++) {
        counter.set(birth[i], 0);
        counter.set(death[i], 0);
    }
    for (let i = 0; i < n; i++) {
        const start = birth[i];
        const end = death[i];
        for (const key of counter.keys()) {
            if (key >= start && key <= end) {
                counter.set(key, (counter.get(key) ?? 0) + 1);
            }
        }
    }
    let res = 0;
    let max = 0;
    for (const [key, val] of counter) {
        if (val === max) {
            res = Math.min(res, key);
        } else if (val > max) {
            res = key;
            max = Math.max(max, val);
        }
    }
    return res;
}
