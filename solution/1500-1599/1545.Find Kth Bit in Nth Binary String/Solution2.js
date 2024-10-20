const findKthBit = (n, k) => String((((k / (k & -k)) >> 1) & 1) ^ (k & 1) ^ 1);
