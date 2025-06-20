int romanToInt(char* s) {
    int map[26] = {0};
    map['I' - 'A'] = 1;
    map['V' - 'A'] = 5;
    map['X' - 'A'] = 10;
    map['L' - 'A'] = 50;
    map['C' - 'A'] = 100;
    map['D' - 'A'] = 500;
    map['M' - 'A'] = 1000;

    int res = 0, i = 0;
    while (s[i]) {
        int val = map[s[i] - 'A'];
        int next = s[i + 1] ? map[s[i + 1] - 'A'] : 0;
        res += (val < next) ? -val : val;
        i++;
    }
    return res;
}
