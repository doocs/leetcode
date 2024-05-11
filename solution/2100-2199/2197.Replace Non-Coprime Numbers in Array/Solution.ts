function replaceNonCoprimes(nums: number[]): number[] {
    const gcd = (a: number, b: number): number => {
        if (b === 0) {
            return a;
        }
        return gcd(b, a % b);
    };
    const stk: number[] = [];
    for (let x of nums) {
        stk.push(x);
        while (stk.length > 1) {
            x = stk.at(-1)!;
            const y = stk.at(-2)!;
            const g = gcd(x, y);
            if (g === 1) {
                break;
            }
            stk.pop();
            stk.pop();
            stk.push(((x * y) / g) | 0);
        }
    }
    return stk;
}
