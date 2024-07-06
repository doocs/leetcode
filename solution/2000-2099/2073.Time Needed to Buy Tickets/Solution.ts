function timeRequiredToBuy(tickets: number[], k: number): number {
    let ans = 0;
    const n = tickets.length;
    for (let i = 0; i < n; ++i) {
        ans += Math.min(tickets[i], i <= k ? tickets[k] : tickets[k] - 1);
    }
    return ans;
}
