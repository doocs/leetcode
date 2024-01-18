class Solution {
public:
    int secondHighest(string s) {
        int mask = 0;
        for (char& c : s)
            if (isdigit(c)) mask |= 1 << c - '0';
        for (int i = 9, cnt = 0; ~i; --i)
            if (mask >> i & 1 && ++cnt == 2) return i;
        return -1;
    }
};