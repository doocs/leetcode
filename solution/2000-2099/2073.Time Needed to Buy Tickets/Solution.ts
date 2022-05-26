function timeRequiredToBuy(tickets: number[], k: number): number {
    const n = tickets.length;
    let target = tickets[k] - 1;
    let ans = 0;
    // round1
    for (let i = 0; i < n; i++) {
        let num = tickets[i];
        if (num <= target) {
            ans += num;
            tickets[i] = 0;
        } else {
            ans += target;
            tickets[i] -= target;
        }
    }

    // round2
    for (let i = 0; i <= k; i++) {
        let num = tickets[i];
        ans += num > 0 ? 1 : 0;
    }
    return ans;
}
