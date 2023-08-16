function fraction(cont: number[]): number[] {
    const dfs = (i: number): number[] => {
        if (i === cont.length - 1) {
            return [cont[i], 1];
        }
        const [a, b] = dfs(i + 1);
        const [x, y] = [a * cont[i] + b, a];
        const g = gcd(x, y);
        return [x / g, y / g];
    };
    const gcd = (a: number, b: number): number => {
        return b === 0 ? a : gcd(b, a % b);
    };
    return dfs(0);
}
