type Fn = (accum: number, curr: number) => number;

function reduce(nums: number[], fn: Fn, init: number): number {
    let acc: number = init;
    for (const x of nums) {
        acc = fn(acc, x);
    }
    return acc;
}
