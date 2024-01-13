int subtractProductAndSum(int n) {
    int x = 1;
    int y = 0;
    for (; n > 0; n /= 10) {
        int v = n % 10;
        x *= v;
        y += v;
    }
    return x - y;
}