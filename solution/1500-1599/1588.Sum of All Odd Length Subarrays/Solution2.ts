function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let [ans, f, g] = [arr[0], arr[0], 0];
    for (let i = 1; i < n; ++i) {
        const ff = g + arr[i] * (Math.floor(i / 2) + 1);
        const gg = f + arr[i] * Math.floor((i + 1) / 2);
        [f, g] = [ff, gg];
        ans += f;
    }
    return ans;
}
