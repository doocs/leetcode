function findComplement(num: number): number {
    return ~num & (2 ** num.toString(2).length - 1);
}
