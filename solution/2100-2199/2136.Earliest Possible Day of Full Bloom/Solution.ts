function earliestFullBloom(plantTime: number[], growTime: number[]): number {
    const n = plantTime.length;
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => growTime[j] - growTime[i]);
    let [ans, t] = [0, 0];
    for (const i of idx) {
        t += plantTime[i];
        ans = Math.max(ans, t + growTime[i]);
    }
    return ans;
}
