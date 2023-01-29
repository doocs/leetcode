function putMarbles(weights: number[], k: number): number {
    const n = weights.length;
    const arr: number[] = [];
    for (let i = 0; i < n - 1; ++i) {
        arr.push(weights[i] + weights[i + 1]);
    }
    arr.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < k - 1; ++i) {
        ans += arr[n - i - 2] - arr[i];
    }
    return ans;
}
