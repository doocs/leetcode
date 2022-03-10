function areNumbersAscending(s: string): boolean {
    let strs = s.split(' ');
    let prev = Number.MIN_SAFE_INTEGER;
    for (let str of strs) {
        let num = Number(str);
        if (!isNaN(num)) {
            if (num <= prev) return false;
            prev = num;
        }
    }
    return true;
}
