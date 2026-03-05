function getHappyString(n: number, k: number): string {
    if (k > 3 * (1 << (n - 1))) {
        return '';
    }
    const cs = 'abc';
    const ans: string[] = [];
    for (let i = 0; i < n; i++) {
        const remain = 1 << (n - i - 1);
        for (const c of cs) {
            if (ans.at(-1) === c) {
                continue;
            }
            if (k <= remain) {
                ans.push(c);
                break;
            }
            k -= remain;
        }
    }
    return ans.join('');
}
