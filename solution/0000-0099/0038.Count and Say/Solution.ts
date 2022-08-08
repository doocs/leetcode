function countAndSay(n: number): string {
    let s = '1';
    for (let i = 1; i < n; i++) {
        let t = '';
        let cur = s[0];
        let count = 1;
        for (let j = 1; j < s.length; j++) {
            if (s[j] !== cur) {
                t += `${count}${cur}`;
                cur = s[j];
                count = 0;
            }
            count++;
        }
        t += `${count}${cur}`;
        s = t;
    }
    return s;
}
