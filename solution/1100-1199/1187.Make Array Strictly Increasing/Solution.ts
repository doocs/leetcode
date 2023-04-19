function makeArrayIncreasing(arr1: number[], arr2: number[]): number {
    arr2.sort((a, b) => a - b);
    let m = 0;
    for (const x of arr2) {
        if (m === 0 || x !== arr2[m - 1]) {
            arr2[m++] = x;
        }
    }
    arr2 = arr2.slice(0, m);
    const inf = 1 << 30;
    arr1 = [-inf, ...arr1, inf];
    const n = arr1.length;
    const f: number[] = new Array(n).fill(inf);
    f[0] = 0;
    const search = (arr: number[], x: number): number => {
        let l = 0;
        let r = arr.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i < n; ++i) {
        if (arr1[i - 1] < arr1[i]) {
            f[i] = f[i - 1];
        }
        const j = search(arr2, arr1[i]);
        for (let k = 1; k <= Math.min(i - 1, j); ++k) {
            if (arr1[i - k - 1] < arr2[j - k]) {
                f[i] = Math.min(f[i], f[i - k - 1] + k);
            }
        }
    }
    return f[n - 1] >= inf ? -1 : f[n - 1];
}
