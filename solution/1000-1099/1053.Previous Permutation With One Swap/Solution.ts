function prevPermOpt1(arr: number[]): number[] {
    const n = arr.length;
    for (let i = n - 1; i > 0; --i) {
        if (arr[i - 1] > arr[i]) {
            for (let j = n - 1; j > i - 1; --j) {
                if (arr[j] < arr[i - 1] && arr[j] !== arr[j - 1]) {
                    const t = arr[i - 1];
                    arr[i - 1] = arr[j];
                    arr[j] = t;
                    return arr;
                }
            }
        }
    }
    return arr;
}
