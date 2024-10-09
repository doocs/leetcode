function missingNumber(arr: number[]): number {
    const d = ((arr.at(-1)! - arr[0]) / arr.length) | 0;
    for (let i = 1; i < arr.length; ++i) {
        if (arr[i] - arr[i - 1] !== d) {
            return arr[i - 1] + d;
        }
    }
    return arr[0];
}
