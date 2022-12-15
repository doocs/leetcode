function getLucky(s: string, k: number): number {
    let ans = '';
    for (const c of s) {
        ans += c.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
    }
    for (let i = 0; i < k; i++) {
        let t = 0;
        for (const v of ans) {
            t += Number(v);
        }
        ans = `${t}`;
    }
    return Number(ans);
}
