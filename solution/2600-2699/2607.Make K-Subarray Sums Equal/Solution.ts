function makeSubKSumEqual(arr: number[], k: number): number {
    const n = arr.length;
    const g = gcd(n, k);
    let ans = 0;
    for (let i = 0; i < g; ++i) {
        const t: number[] = [];
        for (let j = i; j < n; j += g) {
            t.push(arr[j]);
        }
        t.sort((a, b) => a - b);
        const mid = t[t.length >> 1];
        for (const x of t) {
            ans += Math.abs(x - mid);
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}
