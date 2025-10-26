function maxSumOfSquares(num: number, sum: number): string {
    if (num * 9 < sum) {
        return '';
    }

    const k = Math.floor(sum / 9);
    const s = sum % 9;

    let ans = '9'.repeat(k);
    if (s > 0) {
        ans += String.fromCharCode('0'.charCodeAt(0) + s);
    }
    if (ans.length < num) {
        ans += '0'.repeat(num - ans.length);
    }

    return ans;
}
