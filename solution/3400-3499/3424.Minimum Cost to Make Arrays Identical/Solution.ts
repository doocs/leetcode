function minCost(arr: number[], brr: number[], k: number): number {
    const calc = (a: number[], b: number[]) => {
        let ans = 0;
        for (let i = 0; i < a.length; ++i) {
            ans += Math.abs(a[i] - b[i]);
        }
        return ans;
    };
    const c1 = calc(arr, brr);
    arr.sort((a, b) => a - b);
    brr.sort((a, b) => a - b);
    const c2 = calc(arr, brr) + k;
    return Math.min(c1, c2);
}
