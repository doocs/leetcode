function translateNum(num: number): number {
    let a = 1;
    let b = 1;
    const str = num + '';
    for (let i = 1; i < str.length; i++) {
        const val = Number(str[i - 1] + str[i]);
        if (val >= 10 && val < 26) {
            [a, b] = [b, a + b];
        } else {
            a = b;
        }
    }
    return b;
}
