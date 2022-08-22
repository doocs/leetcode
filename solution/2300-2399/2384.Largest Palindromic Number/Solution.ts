function largestPalindromic(num: string): string {
    const count = new Array(10).fill(0);
    for (const c of num) {
        count[c]++;
    }
    while (count.reduce((r, v) => (v % 2 === 1 ? r + 1 : r), 0) > 1) {
        for (let i = 0; i < 10; i++) {
            if (count[i] % 2 === 1) {
                count[i]--;
                break;
            }
        }
    }

    let res = [];
    let oddIndex = -1;
    for (let i = 9; i >= 0; i--) {
        if (count[i] % 2 == 1) {
            oddIndex = i;
            count[i] -= 1;
        }
        res.push(...new Array(count[i] >> 1).fill(i));
    }
    if (oddIndex !== -1) {
        res.push(oddIndex);
    }
    const n = res.length;
    for (let i = 0; i < n; i++) {
        if (res[i] !== 0) {
            res = res.slice(i);
            if (oddIndex !== -1) {
                res.push(...[...res.slice(0, res.length - 1)].reverse());
            } else {
                res.push(...[...res].reverse());
            }
            return res.join('');
        }
    }

    return '0';
}
