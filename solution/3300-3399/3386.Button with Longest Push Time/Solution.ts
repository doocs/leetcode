function buttonWithLongestTime(events: number[][]): number {
    let [ans, t] = events[0];
    for (let k = 1; k < events.length; ++k) {
        const [i, t2] = events[k];
        const d = t2 - events[k - 1][1];
        if (d > t || (d === t && i < ans)) {
            ans = i;
            t = d;
        }
    }
    return ans;
}
