public class Solution
{
    public string SortVowels(string s)
    {
        List<char> vs = new List<char>();
        char[] cs = s.ToCharArray();
        foreach (char c in cs)
        {
            if (IsVowel(c))
                vs.Add(c);
        }
        vs.Sort();
        for (int i = 0, j = 0; i < cs.Length; ++i)
        {
            if (IsVowel(cs[i]))
                cs[i] = vs[j++];
        }
        return new string(cs);
    }

    public bool IsVowel(char c) {
        char d = char.ToLower(c);
        if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u')
        {
            return true;
        }
        return false;
    }
}
