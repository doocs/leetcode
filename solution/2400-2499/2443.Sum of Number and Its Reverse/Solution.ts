function sumOfNumberAndReverse(num: number): boolean {
    for (let i = 0; i <= num; i++) {
        if (i + Number([...(i + '')].reverse().join('')) === num) {
            return true;
        }
    }
    return false;
}
