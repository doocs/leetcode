class Solution {
public:
    string addBinary(string a, string b) {
        string res;
        int carry = 0;

        int i = a.size() - 1;
        int j = b.size() - 1;

        while (i >= 0 || j >= 0) {
            int digitA = i >= 0 ? a.at(i--) - '0' : 0;
            int digitB = j >= 0 ? b.at(j--) - '0' : 0;
            int sum = digitA + digitB + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            res += to_string(sum);
        }

        if (carry == 1) res.push_back('1');
        reverse(res.begin(), res.end());
        return res;
    }
};