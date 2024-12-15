function minSetSize(arr: number[]): number {
    const cnt = new Map<number, number>();
    for (const v of arr) {
        cnt.set(v, (cnt.get(v) ?? 0) + 1);
    }
    let [ans, m] = [0, 0];
    for (const v of Array.from(cnt.values()).sort((a, b) => b - a)) {
        m += v;
        ++ans;
        if (m * 2 >= arr.length) {
            break;
        }
    }
    return ans;
}
