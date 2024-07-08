class Hashing {
private:
    vector<long> p, h;
    long mod;

public:
    Hashing(const string& word, long base, long mod)
        : p(word.size() + 1, 1)
        , h(word.size() + 1, 0)
        , mod(mod) {
        for (int i = 1; i <= word.size(); ++i) {
            p[i] = p[i - 1] * base % mod;
            h[i] = (h[i - 1] * base + word[i - 1]) % mod;
        }
    }

    long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    int minimumCost(string target, vector<string>& words, vector<int>& costs) {
        const int base = 13331;
        const int mod = 998244353;
        const int inf = INT_MAX / 2;

        int n = target.size();
        Hashing hashing(target, base, mod);

        vector<int> f(n + 1, inf);
        f[0] = 0;

        set<int> ss;
        for (const string& w : words) {
            ss.insert(w.size());
        }

        unordered_map<long, int> d;
        for (int i = 0; i < words.size(); ++i) {
            long x = 0;
            for (char c : words[i]) {
                x = (x * base + c) % mod;
            }
            d[x] = d.find(x) == d.end() ? costs[i] : min(d[x], costs[i]);
        }

        for (int i = 1; i <= n; ++i) {
            for (int j : ss) {
                if (j > i) {
                    break;
                }
                long x = hashing.query(i - j + 1, i);
                if (d.contains(x)) {
                    f[i] = min(f[i], f[i - j] + d[x]);
                }
            }
        }

        return f[n] >= inf ? -1 : f[n];
    }
};