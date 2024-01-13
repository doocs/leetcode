function finalValueAfterOperations(operations: string[]): number {
    return operations.reduce((r, v) => r + (v[1] === '+' ? 1 : -1), 0);
}
