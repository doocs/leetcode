class Solution {
public:
    long long countOfSubstrings(string word, int k) {
        auto f = [&](int k) -> long long {
            long long ans = 0;
            int l = 0, x = 0;
            unordered_map<char, int> cnt;
            auto vowel = [&](char c) -> bool {
                return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
            };
            for (char c : word) {
                if (vowel(c)) {
                    cnt[c]++;
                } else {
                    ++x;
                }
                while (x >= k && cnt.size() == 5) {
                    char d = word[l++];
                    if (vowel(d)) {
                        if (--cnt[d] == 0) {
                            cnt.erase(d);
                        }
                    } else {
                        --x;
                    }
                }
                ans += l;
            }
            return ans;
        };

        return f(k) - f(k + 1);
    }
};
