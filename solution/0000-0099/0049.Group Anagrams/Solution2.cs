public class Solution {
    public IList<IList<string>> GroupAnagrams(string[] strs) {
        var d = new Dictionary<string, List<string>>();
        foreach (string s in strs) {
            int[] cnt = new int[26];
            foreach (char c in s) {
                cnt[c - 'a']++;
            }
            string key = string.Join(",", cnt);
            if (!d.ContainsKey(key)) {
                d[key] = new List<string>();
            }
            d[key].Add(s);
        }
        return new List<IList<string>>(d.Values);
    }
}
