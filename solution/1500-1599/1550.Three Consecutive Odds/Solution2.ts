function threeConsecutiveOdds(arr: number[]): boolean {
    const n = arr.length;
    if (n < 3) return false;

    for (let i = 2; i < n; i++) {
        if (arr[i - 2] & arr[i - 1] & arr[i] & 1) {
            return true;
        }
    }
    return false;
}
