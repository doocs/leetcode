public class Solution {
    public bool DoesAliceWin(string s) {
        string vowels = "aeiou";
        foreach (char c in s) {
            if (vowels.Contains(c)) {
                return true;
            }
        }
        return false;
    }
}
