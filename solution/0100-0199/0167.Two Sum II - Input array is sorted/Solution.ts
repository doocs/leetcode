function twoSum(numbers: number[], target: number): number[] {
    for (let right = numbers.length - 1; right >= 0; --right) {
        let left = numbers.indexOf(target - numbers[right]);
        if (left > -1 && left < right) {
            return [left + 1, right + 1];
        }
    }
    return [-1, -1];
};