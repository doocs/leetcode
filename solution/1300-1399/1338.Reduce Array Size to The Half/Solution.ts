function minSetSize(arr: number[]): number {
    const counter = new Map<number, number>();
    for (const v of arr) {
        counter.set(v, (counter.get(v) ?? 0) + 1);
    }
    const t = Array.from(counter.values());
    t.sort((a, b) => b - a);
    let ans = 0;
    let n = 0;
    for (const cnt of t) {
        n += cnt;
        ++ans;
        if (n * 2 >= arr.length) {
            break;
        }
    }
    return ans;
}
