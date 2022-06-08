function maxChunksToSorted(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    let max = 0;
    for (let i = 0; i < n; i++) {
        max = Math.max(arr[i], max);
        if (max == i) {
            ans++;
        }
    }
    return ans;
}
