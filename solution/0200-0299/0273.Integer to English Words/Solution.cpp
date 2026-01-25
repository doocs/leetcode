class Solution {
public:
    vector<string> lt20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    vector<string> tens = {
        "", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
        "Sixty", "Seventy", "Eighty", "Ninety"};

    vector<string> thousands = {"Billion", "Million", "Thousand", ""};

    string numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        string res;
        for (int i = 1000000000, j = 0; i > 0; i /= 1000, ++j) {
            int cur = num / i;
            if (cur == 0) {
                continue;
            }
            if (!res.empty()) {
                res += ' ';
            }
            res += transfer(cur);
            if (!thousands[j].empty()) {
                res += ' ';
                res += thousands[j];
            }
            num %= i;
        }
        return res;
    }

private:
    string transfer(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 20) {
            return lt20[num];
        }
        if (num < 100) {
            if (num % 10 == 0) {
                return tens[num / 10];
            }
            return tens[num / 10] + " " + transfer(num % 10);
        }
        if (num % 100 == 0) {
            return lt20[num / 100] + " Hundred";
        }
        return lt20[num / 100] + " Hundred " + transfer(num % 100);
    }
};
