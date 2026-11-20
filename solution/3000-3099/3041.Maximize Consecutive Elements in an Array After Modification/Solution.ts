function maxSelectedElements(nums: number[]): number {
    let best = 1,
        keep = 1,
        bump = 1,
        last = -10;
    for (const x of nums.sort((a, b) => a - b)) {
        switch (x - last) {
            case 0:
                bump = keep + 1;
                break;
            case 1:
                [keep, bump] = [keep + 1, bump + 1];
                break;
            case 2:
                [keep, bump] = [bump + 1, 1];
                break;
            default:
                keep = bump = 1;
        }
        [best, last] = [Math.max(best, keep, bump), x];
    }
    return best;
}
