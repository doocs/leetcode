class Solution {
public:
    string complexNumberMultiply(string num1, string num2) {
        int a, b, c, d;
        sscanf(num1.c_str(), "%d+%di", &a, &b);
        sscanf(num2.c_str(), "%d+%di", &c, &d);
        return string(to_string(a * c - b * d) + "+" + to_string(a * d + c * b) + "i");
    }
};