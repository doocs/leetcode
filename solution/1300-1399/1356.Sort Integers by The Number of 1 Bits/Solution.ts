function sortByBits(arr: number[]): number[] {
    const countOnes = (num: number) => {
        let count = 0;
        while (num !== 0) {
            num &= num - 1;
            count++;
        }
        return count;
    };
    return arr.sort((a, b) => {
        let res = countOnes(a) - countOnes(b);
        if (res === 0) {
            return a - b;
        }
        return res;
    });
}
