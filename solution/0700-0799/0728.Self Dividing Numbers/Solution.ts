function selfDividingNumbers(left: number, right: number): number[] {
    const check = (x: number): boolean => {
        for (let y = x; y; y = Math.floor(y / 10)) {
            if (y % 10 === 0 || x % (y % 10) !== 0) {
                return false;
            }
        }
        return true;
    };
    return Array.from({ length: right - left + 1 }, (_, i) => i + left).filter(check);
}
