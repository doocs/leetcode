class Hashing {
private:
    vector<long long> p;
    vector<long long> h;
    long long mod;

public:
    Hashing(const string& word, long long base = 13331, long long mod = 998244353) {
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

    long long query(int l, int r) const {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    vector<string> partitionString(const string& s) {
        Hashing hashing(s);
        unordered_set<long long> vis;
        vector<string> ans;
        int l = 1;
        for (int r = 1; r <= (int) s.size(); ++r) {
            long long x = hashing.query(l, r);
            if (!vis.contains(x)) {
                vis.insert(x);
                ans.push_back(s.substr(l - 1, r - l + 1));
                l = r + 1;
            }
        }
        return ans;
    }
};
