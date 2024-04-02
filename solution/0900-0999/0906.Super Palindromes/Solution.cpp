using ll = unsigned long long;

ll ps[2 * 100000];

int init = [] {
    for (int i = 1; i <= 100000; i++) {
        string s = to_string(i);
        string t1 = s;
        reverse(t1.begin(), t1.end());
        string t2 = s.substr(0, s.length() - 1);
        reverse(t2.begin(), t2.end());
        ps[2 * i - 2] = stoll(s + t1);
        ps[2 * i - 1] = stoll(s + t2);
    }
    return 0;
}();

class Solution {
public:
    int superpalindromesInRange(string left, string right) {
        ll l = stoll(left), r = stoll(right);
        int ans = 0;
        for (ll p : ps) {
            ll x = p * p;
            if (x >= l && x <= r && is_palindrome(x)) {
                ++ans;
            }
        }
        return ans;
    }

    bool is_palindrome(ll x) {
        ll y = 0;
        for (ll t = x; t; t /= 10) {
            y = y * 10 + t % 10;
        }
        return x == y;
    }
};