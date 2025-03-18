public class Solution {
    public int MinimizedStringLength(string s) {
        return new HashSet<char>(s).Count;
    }
}
