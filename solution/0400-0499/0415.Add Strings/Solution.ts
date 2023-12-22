function addStrings(num1: string, num2: string): string {
    let i = num1.length - 1;
    let j = num2.length - 1;
    const ans: number[] = [];
    for (let c = 0; i >= 0 || j >= 0 || c; --i, --j) {
        c += i < 0 ? 0 : +num1[i];
        c += j < 0 ? 0 : +num2[j];
        ans.push(c % 10);
        c = Math.floor(c / 10);
    }
    return ans.reverse().join('');
}

function subStrings(num1: string, num2: string): string {
    const m = num1.length;
    const n = num2.length;
    const neg = m < n || (m == n && num1 < num2);
    if (neg) {
        const t = num1;
        num1 = num2;
        num2 = t;
    }
    let i = num1.length - 1;
    let j = num2.length - 1;
    const ans: number[] = [];
    for (let c = 0; i >= 0; --i, --j) {
        c = +num1[i] - c;
        if (j >= 0) {
            c -= +num2[j];
        }
        ans.push((c + 10) % 10);
        c = c < 0 ? 1 : 0;
    }
    while (ans.length > 1 && ans.at(-1) === 0) {
        ans.pop();
    }
    return (neg ? '-' : '') + ans.reverse().join('');
}
