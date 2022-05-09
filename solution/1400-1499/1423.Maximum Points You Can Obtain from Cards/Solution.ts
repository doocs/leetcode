function maxScore(cardPoints: number[], k: number): number {
    const n = cardPoints.length;
    let sum = cardPoints.slice(0, n - k).reduce((r, v) => r + v, 0);
    let min = sum;
    for (let i = 0; i < k; i++) {
        sum += cardPoints[n - k + i] - cardPoints[i];
        min = Math.min(min, sum);
    }
    return cardPoints.reduce((r, v) => r + v, 0) - min;
}
