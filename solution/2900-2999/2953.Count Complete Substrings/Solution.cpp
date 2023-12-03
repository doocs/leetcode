class Solution {
public:
    int countCompleteSubstrings(string word, int k) {
        int n = word.length();
        int ans = 0;
        auto f = [&](string s) {
            int m = s.length();
            int ans = 0;
            for (int i = 1; i <= 26 && i * k <= m; ++i) {
                int l = i * k;
                int cnt[26]{};
                for (int j = 0; j < l; ++j) {
                    ++cnt[s[j] - 'a'];
                }
                unordered_map<int, int> freq;
                for (int x : cnt) {
                    if (x > 0) {
                        freq[x]++;
                    }
                }
                if (freq[k] == i) {
                    ++ans;
                }
                for (int j = l; j < m; ++j) {
                    int a = s[j] - 'a';
                    int b = s[j - l] - 'a';
                    freq[cnt[a]]--;
                    cnt[a]++;
                    freq[cnt[a]]++;

                    freq[cnt[b]]--;
                    cnt[b]--;
                    freq[cnt[b]]++;

                    if (freq[k] == i) {
                        ++ans;
                    }
                }
            }
            return ans;
        };
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && abs(word[j] - word[j - 1]) <= 2) {
                ++j;
            }
            ans += f(word.substr(i, j - i));
            i = j;
        }
        return ans;
    }
};