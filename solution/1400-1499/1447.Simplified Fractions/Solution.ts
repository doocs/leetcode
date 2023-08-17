function simplifiedFractions(n: number): string[] {
    const ans: string[] = [];
    for (let i = 1; i < n; ++i) {
        for (let j = i + 1; j < n + 1; ++j) {
            if (gcd(i, j) === 1) {
                ans.push(`${i}/${j}`);
            }
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
