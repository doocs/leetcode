function minimumNumbers(num: number, k: number): number {
    if (!num) return 0;
    let digit = num % 10;
    for (let i = 1; i < 11; i++) {
        let target = i * k;
        if (target <= num && target % 10 == digit) return i;
    }
    return -1;
}
