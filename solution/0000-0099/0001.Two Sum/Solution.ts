function twoSum(nums: number[], target: number): number[] {
    const m: Map<number, number> = new Map();

    for (let i = 0; ; ++i) {
        const x = nums[i];
        const y = target - x;

        if (m.has(y)) {
            return [m.get(y)!, i];
        }

        m.set(x, i);
    }

}
