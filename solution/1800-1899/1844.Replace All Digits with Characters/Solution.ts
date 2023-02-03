function replaceDigits(s: string): string {
    const n = s.length;
    const ans = [...s];
    for (let i = 1; i < n; i += 2) {
        ans[i] = String.fromCharCode(ans[i - 1].charCodeAt(0) + Number(ans[i]));
    }
    return ans.join('');
}
