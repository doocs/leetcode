function maximumElementAfterDecrementingAndRearranging(arr: number[]): number {
    arr.sort((a, b) => a - b);
    arr[0] = 1;
    let ans = 1;
    for (let i = 1; i < arr.length; ++i) {
        const d = Math.max(0, arr[i] - arr[i - 1] - 1);
        arr[i] -= d;
        ans = Math.max(ans, arr[i]);
    }
    return ans;
};