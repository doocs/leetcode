int integerBreak(int n) {
    if (n < 4) {
        return n - 1;
    }
    int count = (n - 2) / 3;
    return pow(3, count) * (n - count * 3);
}
