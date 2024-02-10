function numberCount(a: number, b: number): number {
    let count: number = 0;
    for (let num = a; num <= b; num++) {
        if (hasUniqueDigits(num)) {
            count++;
        }
    }
    return count;
}
function hasUniqueDigits(num: number): boolean {
    const digits: Set<string> = new Set(num.toString().split(''));
    return digits.size === num.toString().length;
}
