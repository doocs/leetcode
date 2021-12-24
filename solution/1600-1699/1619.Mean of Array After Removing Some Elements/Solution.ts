function trimMean(arr: number[]): number {
    arr.sort((a, b) => a - b);
    let n = arr.length,
        rmLen = n * 0.05;
    let sum = 0;
    for (let i = rmLen; i < n - rmLen; i++) {
        sum += arr[i];
    }
    return sum / (n * 0.9);
}
