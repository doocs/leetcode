function findLengthOfShortestSubarray(arr) {
    let [l, r, n] = [0, arr.length - 1, arr.length];

    while (r && arr[r - 1] <= arr[r]) r--;
    if (r === 0) return 0;

    let ans = r;
    while (l < r && (!l || arr[l - 1] <= arr[l])) {
        while (r < n && arr[l] > arr[r]) r++;
        ans = Math.min(ans, r - l - 1);
        l++;
    }

    return ans;
}
