function maximumUnits(boxTypes: number[][], truckSize: number): number {
    const cnt = new Array(1001).fill(0);
    for (const [a, b] of boxTypes) {
        cnt[b] += a;
    }
    let ans = 0;
    for (let b = 1000; b > 0 && truckSize > 0; --b) {
        const a = cnt[b];
        if (a > 0) {
            ans += b * Math.min(truckSize, a);
            truckSize -= a;
        }
    }
    return ans;
}
