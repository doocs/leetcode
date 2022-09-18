function canBeEqual(target: number[], arr: number[]): boolean {
    const n = target.length;
    const count = new Array(1001).fill(0);
    for (let i = 0; i < n; i++) {
        count[target[i]]++;
        count[arr[i]]--;
    }
    return count.every(v => v === 0);
}
