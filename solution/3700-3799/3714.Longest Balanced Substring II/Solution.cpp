class Solution {
public:
    int longestBalanced(string s) {
        int x = calc1(s);
        int y = max({calc2(s, 'a', 'b'), calc2(s, 'b', 'c'), calc2(s, 'a', 'c')});
        int z = calc3(s);
        return max({x, y, z});
    }

private:
    int calc1(const string& s) {
        int res = 0;
        int i = 0, n = s.size();
        while (i < n) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            res = max(res, j - i);
            i = j;
        }
        return res;
    }

    int calc2(const string& s, char a, char b) {
        int res = 0;
        int i = 0, n = s.size();
        while (i < n) {
            while (i < n && s[i] != a && s[i] != b) {
                ++i;
            }

            unordered_map<int, int> pos;
            pos[0] = i - 1;

            int d = 0;
            while (i < n && (s[i] == a || s[i] == b)) {
                d += (s[i] == a) ? 1 : -1;
                auto it = pos.find(d);
                if (it != pos.end()) {
                    res = max(res, i - it->second);
                } else {
                    pos[d] = i;
                }
                i++;
            }
        }
        return res;
    }

    static long long f(int x, int y) {
        return ((long long) (x + 100000) << 20) | (long long) (y + 100000);
    }

    int calc3(const string& s) {
        unordered_map<long long, int> pos;
        pos[f(0, 0)] = -1;

        int cnt[3] = {0, 0, 0};
        int res = 0;

        for (int i = 0; i < (int) s.size(); i++) {
            char c = s[i];
            ++cnt[c - 'a'];
            int x = cnt[0] - cnt[1];
            int y = cnt[1] - cnt[2];
            long long k = f(x, y);

            auto it = pos.find(k);
            if (it != pos.end()) {
                res = max(res, i - it->second);
            } else {
                pos[k] = i;
            }
        }
        return res;
    }
};
