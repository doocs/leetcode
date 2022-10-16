bool sumOfNumberAndReverse(int num) {
    for (int i = 0; i <= num; i++) {
        int t = i;
        int j = 0;
        while (t > 0) {
            j = j * 10 + t % 10;
            t /= 10;
        }
        if (i + j == num) {
            return 1;
        }
    }
    return 0;
}
