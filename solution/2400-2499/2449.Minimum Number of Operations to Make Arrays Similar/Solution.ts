function makeSimilar(nums: number[], target: number[]): number {
    nums.sort((a, b) => a - b);
    target.sort((a, b) => a - b);

    const a1: number[] = [];
    const a2: number[] = [];
    const b1: number[] = [];
    const b2: number[] = [];

    for (const v of nums) {
        if (v % 2 === 0) {
            a1.push(v);
        } else {
            a2.push(v);
        }
    }

    for (const v of target) {
        if (v % 2 === 0) {
            b1.push(v);
        } else {
            b2.push(v);
        }
    }

    let ans = 0;
    for (let i = 0; i < a1.length; ++i) {
        ans += Math.abs(a1[i] - b1[i]);
    }

    for (let i = 0; i < a2.length; ++i) {
        ans += Math.abs(a2[i] - b2[i]);
    }

    return ans / 4;
}
