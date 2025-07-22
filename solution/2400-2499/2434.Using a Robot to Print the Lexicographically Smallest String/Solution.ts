function robotWithString(s: string): string {
    const cnt = new Map<string, number>();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    const ans: string[] = [];
    const stk: string[] = [];
    let mi = 'a';
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) - 1);
        while (mi < 'z' && (cnt.get(mi) || 0) === 0) {
            mi = String.fromCharCode(mi.charCodeAt(0) + 1);
        }
        stk.push(c);
        while (stk.length > 0 && stk[stk.length - 1] <= mi) {
            ans.push(stk.pop()!);
        }
    }
    return ans.join('');
}
