int subtractProductAndSum(int n) {
    int p = 1;
    int s = 0;
    while (n) {
        int num = n % 10;
        n /= 10;
        p *= num;
        s += num;
    }
    return p - s;
}
