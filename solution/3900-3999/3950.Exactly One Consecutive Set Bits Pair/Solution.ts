function consecutiveSetBits(n: number): boolean {
    let vis = false;
    for (let pre = 0; n > 0; n >>= 1) {
        const cur = n & 1;
        if (pre === cur && cur === 1) {
            if (vis) {
                return false;
            }
            vis = true;
        }
        pre = cur;
    }
    return vis;
}
