function fillCups(amount: number[]): number {
    amount.sort((a, b) => a - b);
    let [a, b, c] = amount;
    let diff = a + b - c;
    if (diff <= 0) return c;
    else return Math.floor((diff + 1) / 2) + c;
}
