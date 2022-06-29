function calculateTax(brackets: number[][], income: number): number {
    let ans = 0;
    let prev = 0;
    for (let [upper, percent] of brackets) {
        if (prev > income) break;
        ans += ((Math.min(upper, income) - prev) * percent) / 100;
        prev = upper;
    }
    return ans;
}
