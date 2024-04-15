function maximumBinaryString(binary: string): string {
    let k = binary.indexOf('0');
    if (k === -1) {
        return binary;
    }
    k += binary.slice(k + 1).split('0').length - 1;
    return '1'.repeat(k) + '0' + '1'.repeat(binary.length - k - 1);
}
