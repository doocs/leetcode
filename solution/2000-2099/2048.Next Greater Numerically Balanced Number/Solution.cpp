class Solution {
public:
    int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < 10000000; ++i) {
            if (check(i)) return i;
        }
        return -1;
    }

    bool check(int num) {
        string s = to_string(num);
        vector<int> counter(10);
        for (char c : s) ++counter[c - '0'];
        for (char c : s) {
            if (counter[c - '0'] != c - '0') return false;
        }
        return true;
    }
};