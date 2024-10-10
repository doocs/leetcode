function replaceElements(arr: number[]): number[] {
    for (let i = arr.length - 1, mx = -1; ~i; --i) {
        const x = arr[i];
        arr[i] = mx;
        mx = Math.max(mx, x);
    }
    return arr;
}
