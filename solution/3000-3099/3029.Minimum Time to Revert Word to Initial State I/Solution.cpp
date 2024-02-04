class Solution {
public:
    int minimumTimeToInitialState(string word, int k) {
        int n = word.length();
        for (int i = 1; i <= 10000; i++) {
            int re = i * k;
            if (re >= n) {
                return i;
            }
            string str = word.substr(re);
            bool flag = true;
            for (int j = 0; j < str.length(); j++) {
                if (str[j] != word[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return 0;
    }
};
