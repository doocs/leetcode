function bagOfTokensScore(tokens: number[], power: number): number {
    tokens.sort((a, b) => a - b);
    let [i, j] = [0, tokens.length - 1];
    let [ans, score] = [0, 0];
    while (i <= j) {
        if (power >= tokens[i]) {
            power -= tokens[i++];
            ans = Math.max(ans, ++score);
        } else if (score) {
            power += tokens[j--];
            score--;
        } else {
            break;
        }
    }
    return ans;
}
