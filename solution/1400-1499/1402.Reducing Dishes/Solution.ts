function maxSatisfaction(satisfaction: number[]): number {
    satisfaction.sort((a, b) => b - a);
    let [ans, s] = [0, 0];
    for (const x of satisfaction) {
        s += x;
        if (s <= 0) {
            break;
        }
        ans += s;
    }
    return ans;
}
