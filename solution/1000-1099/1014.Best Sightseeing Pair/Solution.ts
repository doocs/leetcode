function maxScoreSightseeingPair(values: number[]): number {
    let [ans, mx] = [0, 0];
    for (let j = 0; j < values.length; ++j) {
        ans = Math.max(ans, mx + values[j] - j);
        mx = Math.max(mx, values[j] + j);
    }
    return ans;
}
