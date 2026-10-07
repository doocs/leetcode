function findClosestElements(arr: number[], k: number, x: number): number[] {
    let left = 0;
    let right = arr.length - k;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (x - arr[mid] <= arr[mid + k] - x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return arr.slice(left, left + k);
}
