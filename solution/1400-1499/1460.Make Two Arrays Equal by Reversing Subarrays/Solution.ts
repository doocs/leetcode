function canBeEqual(target: number[], arr: number[]): boolean {
    target.sort((a, b) => a - b);
    arr.sort((a, b) => a - b);
    const n = arr.length;
    for (let i = 0; i < n; i++) {
        if (target[i] !== arr[i]) {
            return false;
        }
    }
    return true;
}
