class Solution {
public:
    int maximumSwap(int num) {
        string s = to_string(num);
        int n = s.size();
        for (int i = 0; i < n - 1; ++i) {
            int mx = i + 1;
            for (int j = i + 1; j < n; ++j) {
                if (s[j] >= s[mx]) mx = j;
            }
            if (s[i] < s[mx]) {
                swap(s[i], s[mx]);
                break;
            }
        }
        return stoi(s);
    }
};