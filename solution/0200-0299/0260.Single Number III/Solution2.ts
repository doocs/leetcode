function singleNumber(nums: number[]): number[] {
    const set = new Set<number>();

    for (const x of nums) {
        if (set.has(x)) set.delete(x);
        else set.add(x);
    }

    return [...set];
}
