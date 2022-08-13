class Solution {
public:
    string largestGoodInteger(string num) {
        for (char i = '9'; i >= '0'; --i) {
            string t(3, i);
            if (num.find(t) != string::npos) return t;
        }
        return "";
    }
};