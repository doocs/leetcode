public class Solution {
    private readonly string[] lt20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private readonly string[] tens = {
        "", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
        "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private readonly string[] thousands = { "Billion", "Million", "Thousand", "" };

    public string NumberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        string res = "";
        for (int i = 1000000000, j = 0; i > 0; i /= 1000, ++j) {
            int cur = num / i;
            if (cur == 0) {
                continue;
            }
            if (res.Length > 0) {
                res += " ";
            }
            res += Transfer(cur);
            if (thousands[j].Length > 0) {
                res += " " + thousands[j];
            }
            num %= i;
        }
        return res;
    }

    private string Transfer(int num) {
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
            return tens[num / 10] + " " + Transfer(num % 10);
        }
        if (num % 100 == 0) {
            return lt20[num / 100] + " Hundred";
        }
        return lt20[num / 100] + " Hundred " + Transfer(num % 100);
    }
}
