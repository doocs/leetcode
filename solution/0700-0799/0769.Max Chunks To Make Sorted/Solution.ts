function maxChunksToSorted(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    let mx = 0;
    for (let i = 0; i < n; i++) {
        mx = Math.max(arr[i], mx);
        if (mx == i) {
            ans++;
        }
    }
    return ans;
}
