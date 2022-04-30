function digitSum(s: string, k: number): string {
    let ans = [];
    while (s.length > k) {
        for (let i = 0; i < s.length; i += k) {
            let cur = s.slice(i, i + k);
            ans.push(cur.split('').reduce((a, c) => a + parseInt(c), 0));
        }
        s = ans.join('');
        ans = [];
    }
    return s;
}
