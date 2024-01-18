class Solution {
public:
    bool patternMatching(string pattern, string value) {
        int n = value.size();
        int cnt[2]{};
        for (char c : pattern) {
            cnt[c - 'a']++;
        }
        if (cnt[0] == 0) {
            return n % cnt[1] == 0 && repeat(value.substr(0, n / cnt[1]), cnt[1]) == value;
        }
        if (cnt[1] == 0) {
            return n % cnt[0] == 0 && repeat(value.substr(0, n / cnt[0]), cnt[0]) == value;
        }
        auto check = [&](int la, int lb) {
            int i = 0;
            string a, b;
            for (char c : pattern) {
                if (c == 'a') {
                    if (!a.empty() && a != value.substr(i, la)) {
                        return false;
                    }
                    a = value.substr(i, la);
                    i += la;
                } else {
                    if (!b.empty() && b != value.substr(i, lb)) {
                        return false;
                    }
                    b = value.substr(i, lb);
                    i += lb;
                }
            }
            return a != b;
        };
        for (int la = 0; la <= n; ++la) {
            if (la * cnt[0] > n) {
                break;
            }
            if ((n - la * cnt[0]) % cnt[1] == 0) {
                int lb = (n - la * cnt[0]) / cnt[1];
                if (check(la, lb)) {
                    return true;
                }
            }
        }
        return false;
    }

    string repeat(string s, int n) {
        string ans;
        while (n--) {
            ans += s;
        }
        return ans;
    }
};