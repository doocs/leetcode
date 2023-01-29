class Solution {
public:
    string addStrings(string num1, string num2) {
        int i = num1.size() - 1, j = num2.size() - 1;
        string ans;
        for (int c = 0; i >= 0 || j >= 0 || c; --i, --j) {
            int a = i < 0 ? 0 : num1[i] - '0';
            int b = j < 0 ? 0 : num2[j] - '0';
            c += a + b;
            ans += to_string(c % 10);
            c /= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }

    string subStrings(string num1, string num2) {
        int m = num1.size(), n = num2.size();
        bool neg = m < n || (m == n && num1 < num2);
        if (neg) {
            swap(num1, num2);
        }
        int i = num1.size() - 1, j = num2.size() - 1;
        string ans;
        for (int c = 0; i >= 0; --i, --j) {
            c = (num1[i] - '0') - c - (j < 0 ? 0 : num2[j] - '0');
            ans += to_string((c + 10) % 10);
            c = c < 0 ? 1 : 0;
        }
        while (ans.size() > 1 && ans.back() == '0') {
            ans.pop_back();
        }
        if (neg) {
            ans.push_back('-');
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};