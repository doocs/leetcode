function getGoodIndices(variables: number[][], target: number): number[] {
    const qpow = (a: number, n: number, mod: number) => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    const ans: number[] = [];
    for (let i = 0; i < variables.length; ++i) {
        const [a, b, c, m] = variables[i];
        if (qpow(qpow(a, b, 10), c, m) === target) {
            ans.push(i);
        }
    }
    return ans;
}
