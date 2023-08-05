function kthLuckyNumber(k: number): string {
    let n = 1;
    while (k > 1 << n) {
        k -= 1 << n;
        ++n;
    }
    const ans: string[] = [];
    while (n-- > 0) {
        if (k <= 1 << n) {
            ans.push('4');
        } else {
            ans.push('7');
            k -= 1 << n;
        }
    }
    return ans.join('');
}
