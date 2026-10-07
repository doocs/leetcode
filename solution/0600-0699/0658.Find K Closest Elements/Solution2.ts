function findClosestElements(arr: number[], k: number, x: number): number[] {
    let l = 0;
    let r = arr.length;
    while (r - l > k) {
        if (x - arr[l] <= arr[r - 1] - x) {
            --r;
        } else {
            ++l;
        }
    }
    return arr.slice(l, r);
}
