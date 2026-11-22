function assignUmbrella(rain: number[]): string[] {
    const n = rain.length;
    let a = 0,
        b = 0;
    const ans: string[] = new Array(n);
    for (let i = 0; i < n; i++) {
        const x = rain[i];
        if (x === 0) {
            ans[i] = 'None';
        } else if (a <= b && i < n - 1 && rain[i + 1] === 1) {
            ans[i] = 'Alice';
            a++;
        } else {
            ans[i] = 'Bob';
            b++;
        }
    }
    return ans;
}
