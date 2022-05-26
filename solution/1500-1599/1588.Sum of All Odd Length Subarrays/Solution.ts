function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let res = 0;
    for (let i = 1; i <= n; i += 2) {
        let sum = 0;
        for (let j = 0; j < i; j++) {
            sum += arr[j];
        }
        res += sum;
        for (let j = i; j < n; j++) {
            sum -= arr[j - i];
            sum += arr[j];
            res += sum;
        }
    }
    return res;
}
