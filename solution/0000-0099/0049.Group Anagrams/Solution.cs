public class Solution {
    public IList<IList<string>> GroupAnagrams(string[] strs) {
        var d = new Dictionary<string, List<string>>();
        foreach (string s in strs) {
            char[] t = s.ToCharArray();
            Array.Sort(t);
            string k = new string(t);
            if (!d.ContainsKey(k)) {
                d[k] = new List<string>();
            }
            d[k].Add(s);
        }
        return new List<IList<string>>(d.Values);
    }
}
