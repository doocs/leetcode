function maxScoreSightseeingPair(values: number[]): number {
    let ans = 0;
    let mx = values[0];
    for (let j = 1; j < values.length; ++j) {
        ans = Math.max(ans, values[j] - j + mx);
        mx = Math.max(mx, values[j] + j);
    }
    return ans;
}
