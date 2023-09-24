function maximumOddBinaryNumber(s: string): string {
    let cnt = 0;
    for (const c of s) {
        cnt += c === '1' ? 1 : 0;
    }
    return '1'.repeat(cnt - 1) + '0'.repeat(s.length - cnt) + '1';
}
