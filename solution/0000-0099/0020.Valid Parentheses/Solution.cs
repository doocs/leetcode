public class Solution {
    public bool IsValid(string s) {
        Stack<char> stk = new Stack<char>();
        Dictionary<char, char> d = new Dictionary<char, char>();
        d.Add('(', ')');
        d.Add('[', ']');
        d.Add('{', '}');
        foreach (char c in s) {
            if (d.ContainsKey(c)) {
                stk.Push(d[c]);
            } else if (stk.Count == 0 || stk.Pop() != c) {
                return false;
            }
        }
        return stk.Count == 0;
    }
}
