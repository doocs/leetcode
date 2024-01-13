function twoSum(numbers: number[], target: number): number[] {
    const n = numbers.length;
    for (let i = 0; ; ++i) {
        const x = target - numbers[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (numbers[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (numbers[l] === x) {
            return [i, l];
        }
    }
}
