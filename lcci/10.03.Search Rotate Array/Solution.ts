function search(arr: number[], target: number): number {
    let [l, r] = [0, arr.length - 1];
    while (arr[l] === arr[r]) {
        --r;
    }
    while (l < r) {
        const mid = (l + r) >> 1;
        if (arr[mid] > arr[r]) {
            if (arr[l] <= target && target <= arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } else if (arr[mid] < arr[r]) {
            if (arr[mid] < target && target <= arr[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } else {
            --r;
        }
    }
    return arr[l] === target ? l : -1;
}
