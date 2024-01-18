function addToArrayForm(num: number[], k: number): number[] {
    const n = num.length;
    const res = [];
    let sum = 0;
    for (let i = 0; i < n || sum !== 0 || k !== 0; i++) {
        sum += num[n - i - 1] ?? 0;
        sum += k % 10;
        res.push(sum % 10);
        k = Math.floor(k / 10);
        sum = Math.floor(sum / 10);
    }
    return res.reverse();
}
