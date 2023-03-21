function numOfSubarrays(arr: number[], k: number, threshold: number): number {
    let s = arr.slice(0, k).reduce((acc, cur) => acc + cur, 0);
    let ans = s >= k * threshold ? 1 : 0;
    for (let i = k; i < arr.length; ++i) {
        s += arr[i] - arr[i - k];
        ans += s >= k * threshold ? 1 : 0;
    }
    return ans;
}
