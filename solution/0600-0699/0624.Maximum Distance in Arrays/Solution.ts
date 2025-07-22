function maxDistance(arrays: number[][]): number {
    let ans = 0;
    let [mi, mx] = [arrays[0][0], arrays[0].at(-1)!];
    for (let i = 1; i < arrays.length; ++i) {
        const arr = arrays[i];
        const a = Math.abs(arr[0] - mx);
        const b = Math.abs(arr.at(-1)! - mi);
        ans = Math.max(ans, a, b);
        mi = Math.min(mi, arr[0]);
        mx = Math.max(mx, arr.at(-1)!);
    }
    return ans;
}
