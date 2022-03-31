function twoSum(numbers: number[], target: number): number[] {
    let i = 1,
        j = numbers.length;
    while (i < j) {
        const x = numbers[i - 1] + numbers[j - 1];
        if (x == target) {
            return [i, j];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
    return [-1, -1];
}
