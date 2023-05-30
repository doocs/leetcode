function minimumAbsDifference(arr: number[]): number[][] {
    arr.sort((a, b) => a - b);
    let mi = 1 << 30;
    const n = arr.length;
    for (let i = 0; i < n - 1; ++i) {
        mi = Math.min(mi, arr[i + 1] - arr[i]);
    }
    const ans: number[][] = [];
    for (let i = 0; i < n - 1; ++i) {
        if (arr[i + 1] - arr[i] === mi) {
            ans.push([arr[i], arr[i + 1]]);
        }
    }
    return ans;
}
