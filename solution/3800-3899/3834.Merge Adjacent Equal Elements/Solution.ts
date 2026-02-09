function mergeAdjacent(nums: number[]): number[] {
    const stk: number[] = [];
    for (const x of nums) {
        stk.push(x);
        while (stk.length > 1 && stk.at(-1)! === stk.at(-2)!) {
            const a = stk.pop()!;
            const b = stk.pop()!;
            stk.push(a + b);
        }
    }
    return stk;
}
