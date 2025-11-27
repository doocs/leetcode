function longestMountain(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    for (let l = 0, r = 0; l + 2 < n; l = r) {
        r = l + 1;
        if (arr[l] < arr[r]) {
            while (r + 1 < n && arr[r] < arr[r + 1]) {
                ++r;
            }
            if (r + 1 < n && arr[r] > arr[r + 1]) {
                while (r + 1 < n && arr[r] > arr[r + 1]) {
                    ++r;
                }
                ans = Math.max(ans, r - l + 1);
            } else {
                ++r;
            }
        }
    }
    return ans;
}
