char* intToRoman(int num) {
    static char res[20];
    res[0] = '\0';

    int vals[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    char* syms[] = {"M", "CM", "D", "CD", "C", "XC", "L",
        "XL", "X", "IX", "V", "IV", "I"};

    for (int i = 0; i < 13; i++) {
        while (num >= vals[i]) {
            strcat(res, syms[i]);
            num -= vals[i];
        }
    }
    return res;
}
