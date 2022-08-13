using LL = long long;

class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        string res = "";
        bool neg = (numerator > 0) ^ (denominator > 0);
        if (neg) res += "-";
        LL num = abs(numerator);
        LL d = abs(denominator);
        res += to_string(num / d);
        num %= d;
        if (num == 0) return res;
        res += ".";
        unordered_map<LL, int> mp;
        while (num) {
            mp[num] = res.size();
            num *= 10;
            res += to_string(num / d);
            num %= d;
            if (mp.count(num)) {
                int idx = mp[num];
                res.insert(idx, "(");
                res += ")";
                break;
            }
        }
        return res;
    }
};