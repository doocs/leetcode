function removeDigit(number: string, digit: string): string {
    const n = number.length;
    let last = -1;
    for (let i = 0; i < n; ++i) {
        if (number[i] === digit) {
            last = i;
            if (i + 1 < n && number[i] < number[i + 1]) {
                break;
            }
        }
    }
    return number.substring(0, last) + number.substring(last + 1);
}
