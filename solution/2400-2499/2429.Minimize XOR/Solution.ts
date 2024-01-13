function minimizeXor(num1: number, num2: number): number {
    let cnt = 0;
    while (num2) {
        num2 &= num2 - 1;
        ++cnt;
    }
    let x = 0;
    for (let i = 30; i >= 0 && cnt > 0; --i) {
        if ((num1 >> i) & 1) {
            x |= 1 << i;
            --cnt;
        }
    }
    for (let i = 0; cnt > 0; ++i) {
        if (!((num1 >> i) & 1)) {
            x |= 1 << i;
            --cnt;
        }
    }
    return x;
}
