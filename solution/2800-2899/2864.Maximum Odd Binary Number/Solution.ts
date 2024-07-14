function maximumOddBinaryNumber(s: string): string {
    const cnt = s.length - s.replace(/1/g, '').length;
    return '1'.repeat(cnt - 1) + '0'.repeat(s.length - cnt) + '1';
}
