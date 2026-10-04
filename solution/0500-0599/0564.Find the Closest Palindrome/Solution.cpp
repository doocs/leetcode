using i128 = __int128_t;

class Solution {
public:
    string nearestPalindromic(string n) {
        i128 x = parse(n);
        int l = n.size();
        set<i128> res;
        res.insert(pow10(l - 1) - 1);
        res.insert(pow10(l) + 1);
        i128 left = parse(n.substr(0, (l + 1) / 2));
        for (int d = -1; d <= 1; ++d) {
            i128 i = left + d;
            i128 j = l % 2 == 0 ? i : i / 10;
            while (j) {
                i = i * 10 + j % 10;
                j /= 10;
            }
            res.insert(i);
        }
        res.erase(x);
        i128 ans = -1;
        for (i128 t : res) {
            i128 dt = iabs(t - x);
            i128 da = iabs(ans - x);
            if (ans == -1 || dt < da || (dt == da && t < ans)) {
                ans = t;
            }
        }
        return toStr(ans);
    }

private:
    i128 parse(const string& s) {
        i128 x = 0;
        for (char c : s) {
            x = x * 10 + (c - '0');
        }
        return x;
    }

    i128 pow10(int k) {
        i128 x = 1;
        while (k--) {
            x *= 10;
        }
        return x;
    }

    i128 iabs(i128 x) {
        return x < 0 ? -x : x;
    }

    string toStr(i128 x) {
        if (x == 0) {
            return "0";
        }
        string s;
        while (x) {
            s.push_back(char('0' + x % 10));
            x /= 10;
        }
        reverse(s.begin(), s.end());
        return s;
    }
};
