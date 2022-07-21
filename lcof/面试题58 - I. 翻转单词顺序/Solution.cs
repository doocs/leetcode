public class Solution {
    public string ReverseWords(string s) {
        string[] tmp = s.Split(' ', StringSplitOptions.RemoveEmptyEntries);
        Stack<string> ss = new Stack<string>();
        string res = "";

        foreach (var i in tmp) {
            ss.Push(i);
        }

        while (ss.Count > 0) {
            res += ss.Pop();
            if (ss.Count > 0) {
                res += " ";
            }
        }
        return res;
    }
}