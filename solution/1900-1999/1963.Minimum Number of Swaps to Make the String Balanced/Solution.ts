function minSwaps(s: string): number {
    let x = 0;
    for (const c of s) {
        if (c === '[') {
            ++x;
        } else if (x) {
            --x;
        }
    }
    return (x + 1) >> 1;
}
