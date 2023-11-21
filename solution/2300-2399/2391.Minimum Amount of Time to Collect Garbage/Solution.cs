public class Solution {
    public int GarbageCollection(string[] garbage, int[] travel) {
        int len = garbage.Length;
        int res = 0;
        HashSet<char> s = new HashSet<char>();
        for (int i = len - 1; i >= 0; i--) {
            foreach (char ch in garbage[i].ToCharArray()) {
                if (!s.Contains(ch))
                    s.Add(ch);
            }
            res += garbage[i].Length;
            res += i > 0 ? s.Count * travel[i - 1] : 0;
        }
        return res;
    }
}
