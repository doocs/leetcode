public class Solution {
    public int TitleToNumber(string columnTitle) {
        int ans = 0;
        foreach (char c in columnTitle) {
            ans = ans * 26 + c - 'A' + 1;
        }
        return ans;
    }
}