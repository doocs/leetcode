function relocateMarbles(nums: number[], moveFrom: number[], moveTo: number[]): number[] {
    const pos: Set<number> = new Set(nums);
    for (let i = 0; i < moveFrom.length; i++) {
        pos.delete(moveFrom[i]);
        pos.add(moveTo[i]);
    }
    return [...pos].sort((a, b) => a - b);
}
