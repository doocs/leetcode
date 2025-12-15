function bestClosingTime(customers: string): number {
    let ans = 0;
    let cost = 0;
    for (const ch of customers) {
        if (ch === 'Y') {
            cost++;
        }
    }
    let mn = cost;

    for (let j = 1; j <= customers.length; j++) {
        const c = customers[j - 1];
        cost += c === 'N' ? 1 : -1;
        if (cost < mn) {
            mn = cost;
            ans = j;
        }
    }
    return ans;
}
