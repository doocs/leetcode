function minSwaps(data: number[]): number {
    const k = data.reduce((acc, cur) => acc + cur, 0);
    let t = data.slice(0, k).reduce((acc, cur) => acc + cur, 0);
    let mx = t;
    for (let i = k; i < data.length; ++i) {
        t += data[i] - data[i - k];
        mx = Math.max(mx, t);
    }
    return k - mx;
}
