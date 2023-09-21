function subSort(array: number[]): number[] {
    const n = array.length;
    let [mi, mx] = [Infinity, -Infinity];
    let [left, right] = [-1, -1];
    for (let i = 0; i < n; ++i) {
        if (array[i] < mx) {
            right = i;
        } else {
            mx = array[i];
        }
    }
    for (let i = n - 1; ~i; --i) {
        if (array[i] > mi) {
            left = i;
        } else {
            mi = array[i];
        }
    }
    return [left, right];
}
