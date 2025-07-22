function countTriplets(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let s = arr[i];
        for (let k = i + 1; k < n; ++k) {
            s ^= arr[k];
            if (s === 0) {
                ans += k - i;
            }
        }
    }
    return ans;
}
