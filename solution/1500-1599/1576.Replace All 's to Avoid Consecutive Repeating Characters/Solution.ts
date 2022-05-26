function modifyString(s: string): string {
    const strArr = s.split('');
    const n = s.length;
    for (let i = 0; i < n; i++) {
        if (strArr[i] === '?') {
            const before = strArr[i - 1];
            const after = strArr[i + 1];

            if (after !== 'a' && before !== 'a') {
                strArr[i] = 'a';
            } else if (after !== 'b' && before !== 'b') {
                strArr[i] = 'b';
            } else {
                strArr[i] = 'c';
            }
        }
    }
    return strArr.join('');
}
