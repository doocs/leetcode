class Solution {
public:
    int longestBeautifulSubstring(string word) {
        vector<pair<char, int>> arr;
        int n = word.size();
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && word[j] == word[i]) ++j;
            arr.push_back({word[i], j - i});
            i = j;
        }
        int ans = 0;
        for (int i = 0; i < (int) arr.size() - 4; ++i) {
            auto& [a, v1] = arr[i];
            auto& [b, v2] = arr[i + 1];
            auto& [c, v3] = arr[i + 2];
            auto& [d, v4] = arr[i + 3];
            auto& [e, v5] = arr[i + 4];
            if (a == 'a' && b == 'e' && c == 'i' && d == 'o' && e == 'u') {
                ans = max(ans, v1 + v2 + v3 + v4 + v5);
            }
        }
        return ans;
    }
};