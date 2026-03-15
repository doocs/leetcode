function firstUniqueEven(nums: number[]): number {
    const cnt: number[] = new Array(101).fill(0);

    for (const x of nums) {
        cnt[x]++;
    }

    for (const x of nums) {
        if (x % 2 === 0 && cnt[x] === 1) {
            return x;
        }
    }

    return -1;
}
