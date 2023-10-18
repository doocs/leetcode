class Solution {
public:
    string printBin(double num) {
        string ans = "0.";
        while (ans.size() < 32 && num != 0) {
            num *= 2;
            int x = (int) num;
            ans.push_back('0' + x);
            num -= x;
        }
        return num != 0 ? "ERROR" : ans;
    }
};