function maximumUnits(boxTypes: number[][], truckSize: number): number {
    boxTypes.sort((a, b) => b[1] - a[1]);
    let sum = 0;
    let ans = 0;
    for (const [count, size] of boxTypes) {
        if (sum + count < truckSize) {
            ans += size * count;
            sum += count;
        } else {
            ans += (truckSize - sum) * size;
            break;
        }
    }
    return ans;
}
