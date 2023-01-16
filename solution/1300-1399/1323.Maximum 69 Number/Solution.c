int maximum69Number(int num) {
    int n = 0;
    int i = 0;
    int t = num;
    while (t) {
        n++;
        if (t % 10 == 6) {
            i = n;
        }
        t /= 10;
    }
    return num + 3 * pow(10, i - 1);
}
