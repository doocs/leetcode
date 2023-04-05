function minimizeXor(num1: number, num2: number): number {
    let cnt1 = bitCount(num1);
    let cnt2 = bitCount(num2);
    for (; cnt1 > cnt2; --cnt1) {
        num1 &= num1 - 1;
    }
    for (; cnt1 < cnt2; ++cnt1) {
        num1 |= num1 + 1;
    }
    return num1;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
