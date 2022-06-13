function findTheDistanceValue(
    arr1: number[],
    arr2: number[],
    d: number,
): number {
    arr2.sort((a, b) => a - b);
    const n = arr2.length;
    let res = 0;
    for (const num of arr1) {
        let left = 0;
        let right = n - 1;
        while (left < right) {
            const mid = (left + right) >>> 1;
            if (arr2[mid] <= num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (
            Math.abs(num - arr2[left]) <= d ||
            (left !== 0 && Math.abs(num - arr2[left - 1]) <= d)
        ) {
            continue;
        }
        res++;
    }
    return res;
}
