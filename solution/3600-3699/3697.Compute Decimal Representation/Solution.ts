function decimalRepresentation(n: number): number[] {
    const ans: number[] = [];
    let p: number = 1;
    while (n) {
        const v = n % 10;
        n = (n / 10) | 0;
        if (v) {
            ans.push(p * v);
        }
        p *= 10;
    }
    ans.reverse();
    return ans;
}
