function averageWaitingTime(customers: number[][]): number {
    let [tot, t] = [0, 0];
    for (const [a, b] of customers) {
        t = Math.max(t, a) + b;
        tot += t - a;
    }
    return tot / customers.length;
}
