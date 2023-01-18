function calculateTax(brackets: number[][], income: number): number {
    let ans = 0;
    let prev = 0;
    for (const [upper, percent] of brackets) {
        ans += Math.max(0, Math.min(income, upper) - prev) * percent;
        prev = upper;
    }
    return ans / 100;
}
