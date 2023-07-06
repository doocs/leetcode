function sumOfThree(num: number): number[] {
    if (num % 3) {
        return [];
    }
    const x = Math.floor(num / 3);
    return [x - 1, x, x + 1];
}
