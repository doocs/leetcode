class Solution {
public:
    int minimumTimeToInitialState(string word, int k) {
        int n = word.size();
        for (int i = k; i < n; i += k) {
            if (word.substr(i) == word.substr(0, n - i)) {
                return i / k;
            }
        }
        return (n + k - 1) / k;
    }
};