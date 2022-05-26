function multiply(num1: string, num2: string): string {
    if ([num1, num2].includes('0')) return '0';
    const n1 = num1.length,
        n2 = num2.length;
    let ans = '';
    for (let i = 0; i < n1; i++) {
        let cur1 = parseInt(num1.charAt(n1 - i - 1), 10);
        let sum = '';
        for (let j = 0; j < n2; j++) {
            let cur2 = parseInt(num2.charAt(n2 - j - 1), 10);
            sum = addString(sum, cur1 * cur2 + '0'.repeat(j));
        }
        ans = addString(ans, sum + '0'.repeat(i));
    }
    return ans;
}

function addString(s1: string, s2: string): string {
    const n1 = s1.length,
        n2 = s2.length;
    let ans = [];
    let sum = 0;
    for (let i = 0; i < n1 || i < n2 || sum > 0; i++) {
        let num1 = i < n1 ? parseInt(s1.charAt(n1 - i - 1), 10) : 0;
        let num2 = i < n2 ? parseInt(s2.charAt(n2 - i - 1), 10) : 0;
        sum += num1 + num2;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans.join('');
}
