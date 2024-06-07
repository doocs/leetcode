function lexicalOrder(n: number): number[] {
    const ans: number[] = [];
    let v = 1;
    for (let i = 0; i < n; ++i) {
        ans.push(v);
        if (v * 10 <= n) {
            v *= 10;
        } else {
            while (v % 10 === 9 || v === n) {
                v = Math.floor(v / 10);
            }
            ++v;
        }
    }
    return ans;
}
