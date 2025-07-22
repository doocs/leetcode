class Hashing {
private:
    vector<long long> p;
    vector<long long> h;
    long long mod;

public:
    Hashing(const string& word, long long base, int mod) {
        int n = word.size();
        p.resize(n + 1);
        h.resize(n + 1);
        p[0] = 1;
        this->mod = mod;
        for (int i = 1; i <= n; i++) {
            p[i] = (p[i - 1] * base) % mod;
            h[i] = (h[i - 1] * base + word[i - 1]) % mod;
        }
    }

    long long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    int minValidStrings(vector<string>& words, string target) {
        int base = 13331, mod = 998244353;
        Hashing hashing(target, base, mod);
        int m = 0, n = target.size();
        for (const string& word : words) {
            m = max(m, (int) word.size());
        }

        vector<unordered_set<long long>> s(m + 1);
        for (const string& w : words) {
            long long h = 0;
            for (int j = 0; j < w.size(); j++) {
                h = (h * base + w[j]) % mod;
                s[j + 1].insert(h);
            }
        }

        auto f = [&](int i) -> int {
            int l = 0, r = min(n - i, m);
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                long long sub = hashing.query(i + 1, i + mid);
                if (s[mid].count(sub)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return l;
        };

        int ans = 0, last = 0, mx = 0;
        for (int i = 0; i < n; i++) {
            int dist = f(i);
            mx = max(mx, i + dist);
            if (i == last) {
                if (i == mx) {
                    return -1;
                }
                last = mx;
                ans++;
            }
        }
        return ans;
    }
};
