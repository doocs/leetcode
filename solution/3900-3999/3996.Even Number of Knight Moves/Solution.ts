function canReach(start: number[], target: number[]): boolean {
    return (start[0] + start[1]) % 2 === (target[0] + target[1]) % 2;
}
