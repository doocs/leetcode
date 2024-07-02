export function maximumUnits(boxTypes: number[][], truckSize: number): number {
    boxTypes.sort(([_, a], [__, b]) => b - a);
    let ans = 0;
    for (const [count, size] of boxTypes) {
        ans += Math.min(truckSize, count) * size;
        truckSize -= count;
        if (truckSize < 0) break;
    }
    return ans;
}
