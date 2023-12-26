function buyChoco(prices: number[], money: number): number {
    let [a, b] = [1000, 1000];
    for (const x of prices) {
        if (x < a) {
            b = a;
            a = x;
        } else if (x < b) {
            b = x;
        }
    }
    const cost = a + b;
    return money < cost ? money : money - cost;
}
