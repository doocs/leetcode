function maxWeight(pizzas: number[]): number {
    const n = pizzas.length;
    const days = n >> 2;
    pizzas.sort((a, b) => a - b);
    const odd = (days + 1) >> 1;
    let even = days - odd;
    let ans = 0;
    for (let i = n - odd; i < n; ++i) {
        ans += pizzas[i];
    }
    for (let i = n - odd - 2; even; --even) {
        ans += pizzas[i];
        i -= 2;
    }
    return ans;
}
