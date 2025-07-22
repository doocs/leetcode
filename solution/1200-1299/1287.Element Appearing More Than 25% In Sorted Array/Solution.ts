function findSpecialInteger(arr: number[]): number {
    const n = arr.length;
    for (let i = 0; ; ++i) {
        if (arr[i] === arr[i + (n >> 2)]) {
            return arr[i];
        }
    }
}
