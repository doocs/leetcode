function sortByReflection(nums: number[]): number[] {
    const f = (x: number): number => {
        let y = 0;
        for (; x; x >>= 1) {
            y = (y << 1) | (x & 1);
        }
        return y;
    };

    nums.sort((a, b) => {
        const fa = f(a);
        const fb = f(b);
        if (fa !== fb) {
            return fa - fb;
        }
        return a - b;
    });

    return nums;
}
