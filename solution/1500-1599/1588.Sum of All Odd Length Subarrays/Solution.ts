function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        for (let j = i; j < n; ++j) {
            s += arr[j];
            if ((j - i + 1) % 2 === 1) {
                ans += s;
            }
        }
    }
    return ans;
}
