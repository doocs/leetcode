function maxSatisfied(customers: number[], grumpy: number[], minutes: number): number {
    let [cnt, tot] = [0, 0];
    for (let i = 0; i < minutes; ++i) {
        cnt += customers[i] * grumpy[i];
        tot += customers[i] * (grumpy[i] ^ 1);
    }
    let mx = cnt;
    for (let i = minutes; i < customers.length; ++i) {
        cnt += customers[i] * grumpy[i];
        cnt -= customers[i - minutes] * grumpy[i - minutes];
        mx = Math.max(mx, cnt);
        tot += customers[i] * (grumpy[i] ^ 1);
    }
    return tot + mx;
}
