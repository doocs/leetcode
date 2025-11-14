class Hashing {
public:
    vector<long long> p, h;
    long long mod;

    Hashing(const string& word)
        : Hashing(word, 13331, 998244353) {}

    Hashing(const string& word, long long base, long long mod)
        : mod(mod) {
        int n = word.size();
        p.assign(n + 1, 1);
        h.assign(n + 1, 0);
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * base % mod;
            h[i] = (h[i - 1] * base + word[i - 1]) % mod;
        }
    }

    long long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    int maxFreq(string s, int maxLetters, int minSize, int maxSize) {
        int n = s.size();
        Hashing hashing(s);
        vector<int> freq(256, 0);
        int k = 0;
        int ans = 0;
        unordered_map<long long, int> cnt;

        for (int i = 1; i <= n; i++) {
            if (++freq[s[i - 1]] == 1) {
                k++;
            }

            if (i >= minSize) {
                if (k <= maxLetters) {
                    long long x = hashing.query(i - minSize + 1, i);
                    ans = max(ans, ++cnt[x]);
                }
                int j = i - minSize;
                if (--freq[s[j]] == 0) {
                    k--;
                }
            }
        }

        return ans;
    }
};
