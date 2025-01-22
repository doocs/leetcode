function finalValueAfterOperations(operations: string[]): number {
    return operations.reduce((acc, op) => acc + (op[1] === '+' ? 1 : -1), 0);
}
