function convertInteger(A: number, B: number): number {
    let res = 0;
    while (A !== 0 || B !== 0) {
        if ((A & 1) !== (B & 1)) {
            res++;
        }
        A >>>= 1;
        B >>>= 1;
    }
    return res;
}
