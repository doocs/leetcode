const findKthBit = (n: number, k: number): string =>
    String((((k / (k & -k)) >> 1) & 1) ^ (k & 1) ^ 1);
