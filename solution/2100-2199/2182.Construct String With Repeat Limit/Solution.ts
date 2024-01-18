function repeatLimitedString(s: string, repeatLimit: number): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    const ans: string[] = [];
    for (let i = 25, j = 24; ~i; --i) {
        j = Math.min(j, i - 1);
        while (true) {
            for (let k = Math.min(cnt[i], repeatLimit); k; --k) {
                ans.push(String.fromCharCode(97 + i));
                --cnt[i];
            }
            if (!cnt[i]) {
                break;
            }
            while (j >= 0 && !cnt[j]) {
                --j;
            }
            if (j < 0) {
                break;
            }
            ans.push(String.fromCharCode(97 + j));
            --cnt[j];
        }
    }
    return ans.join('');
}
