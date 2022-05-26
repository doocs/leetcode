function maxChunksToSorted(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    let max = 0;
    for (let i = 0; i < n; i++) {
        let cur = arr[i];
        max = Math.max(cur, max);
        if (max == i) {
            ans++;
        }
    }
    return ans;
}
