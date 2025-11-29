function smallestPalindrome(s: string): string {
    const ascii_lowercase = 'abcdefghijklmnopqrstuvwxyz';
    const cnt = new Array<number>(26).fill(0);
    for (const chKey of s) {
        cnt[chKey.charCodeAt(0) - 97]++;
    }

    const t: string[] = [];
    let ch = '';
    for (let i = 0; i < 26; i++) {
        const c = ascii_lowercase[i];
        const v = Math.floor(cnt[i] / 2);
        t.push(c.repeat(v));
        cnt[i] -= v * 2;
        if (cnt[i] === 1) {
            ch = c;
        }
    }

    let ans = t.join('');
    ans = ans + ch + ans.split('').reverse().join('');
    return ans;
}
