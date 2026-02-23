function maximumXor(s: string, t: string): string {
    const cnt = [0, 0];

    for (const c of t) {
        cnt[Number(c)]++;
    }

    const ans: string[] = new Array(s.length).fill('0');

    for (let i = 0; i < s.length; i++) {
        const x = Number(s[i]);
        if (cnt[x ^ 1] > 0) {
            cnt[x ^ 1]--;
            ans[i] = '1';
        } else {
            cnt[x]--;
        }
    }

    return ans.join('');
}
