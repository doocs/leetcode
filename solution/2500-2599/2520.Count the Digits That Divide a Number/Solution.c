int countDigits(int num) {
    int ans = 0;
    int cur = num;
    while (cur) {
        if (num % (cur % 10) == 0) {
            ans++;
        }
        cur /= 10;
    }
    return ans;
}
