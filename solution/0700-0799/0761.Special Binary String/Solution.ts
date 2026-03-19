function makeLargestSpecial(s: string): string {
    if (s.length === 0) {
        return '';
    }

    const ans: string[] = [];
    let cnt = 0;

    for (let i = 0, j = 0; i < s.length; ++i) {
        cnt += s[i] === '1' ? 1 : -1;
        if (cnt === 0) {
            const t = '1' + makeLargestSpecial(s.substring(j + 1, i)) + '0';
            ans.push(t);
            j = i + 1;
        }
    }

    ans.sort((a, b) => b.localeCompare(a));
    return ans.join('');
}
