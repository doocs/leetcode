function simplifiedFractions(n: number): string[] {
    let ans: Array<string> = [];
    for (let j = 2; j <= n; j++) {
        for (let i = 1; i < j; i++) {
            if (gcd(i, j) == 1) {
                ans.push(`${i}/${j}`);
            }
        }
    }
    return ans;
}

// a < b
function gcd(a: number, b: number): number {
    if (a > b) [a, b] = [b, a];
    while (a) {
        [a, b] = [b % a, a];
    }
    return b;
}
