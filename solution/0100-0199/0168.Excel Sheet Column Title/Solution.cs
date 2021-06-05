public class Solution {
    public string ConvertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber != 0) {
            --columnNumber;
            res.Append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return new string(res.ToString().Reverse().ToArray());
    }
}