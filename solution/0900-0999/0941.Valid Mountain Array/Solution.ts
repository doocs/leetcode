function validMountainArray(arr: number[]): boolean {
    const n = arr.length;
    if (n < 3) {
        return false;
    }
    let [i, j] = [0, n - 1];
    while (i + 1 < n - 1 && arr[i] < arr[i + 1]) {
        i++;
    }
    while (j - 1 > 0 && arr[j] < arr[j - 1]) {
        j--;
    }
    return i === j;
}
