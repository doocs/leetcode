function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let res = 0;
    for (let i = 1; i <= n; i += 2) {
        let sum = arr.slice(0, i).reduce((r, v) => r + v);
        res += sum;
        for (let j = i; j < n; j++) {
            sum += arr[j] - arr[j - i];
            res += sum;
        }
    }
    return res;
}
