function sortByBits(arr: number[]): number[] {
    const countOnes = (n: number) => {
        let res = 0;
        while (n) {
            n &= n - 1;
            res++;
        }
        return res;
    };
    return arr.sort((a, b) => countOnes(a) - countOnes(b) || a - b);
}
