function reverseWords(s: string): string {
    return s
        .split(/\s+/)
        .map(str => {
            let res = '';
            for (const c of str) {
                res = c + res;
            }
            return res;
        })
        .join(' ');
}
