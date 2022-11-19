function canMakeArithmeticProgression(arr: number[]): boolean {
    arr.sort((a, b) => a - b);
    const n = arr.length;
    for (let i = 2; i < n; i++) {
        if (arr[i - 2] - arr[i - 1] !== arr[i - 1] - arr[i]) {
            return false;
        }
    }
    return true;
}
