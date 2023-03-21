function findOriginalArray(changed: number[]): number[] {
    const n = changed.length;
    if (n & 1) {
        return [];
    }
    const cnt = new Map<number, number>();
    for (const x of changed) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    changed.sort((a, b) => a - b);
    const ans: number[] = [];
    for (const x of changed) {
        if (cnt.get(x) == 0) {
            continue;
        }
        if ((cnt.get(x * 2) || 0) <= 0) {
            return [];
        }
        ans.push(x);
        cnt.set(x, (cnt.get(x) || 0) - 1);
        cnt.set(x * 2, (cnt.get(x * 2) || 0) - 1);
    }
    return ans.length == n / 2 ? ans : [];
}
