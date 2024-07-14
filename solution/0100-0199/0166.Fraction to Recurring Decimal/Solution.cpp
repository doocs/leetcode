class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        string ans;
        bool neg = (numerator > 0) ^ (denominator > 0);
        if (neg) {
            ans += "-";
        }
        long long a = abs(1LL * numerator), b = abs(1LL * denominator);
        ans += to_string(a / b);
        a %= b;
        if (a == 0) {
            return ans;
        }
        ans += ".";
        unordered_map<long long, int> d;
        while (a) {
            d[a] = ans.size();
            a *= 10;
            ans += to_string(a / b);
            a %= b;
            if (d.contains(a)) {
                ans.insert(d[a], "(");
                ans += ")";
                break;
            }
        }
        return ans;
    }
};