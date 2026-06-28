function maximumElementAfterDecrementingAndRearranging(arr: number[]): number {
    arr.sort((a, b) => a - b);

    arr[0] = 1;
    for (let i = 1; i < arr.length; i++) {
        arr[i] = Math.min(arr[i], arr[i - 1] + 1);
    }

    return arr.at(-1)!;
}
