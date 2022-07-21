public class Solution {
    public int[] PrintNumbers(int n) {
        List<int> ans = new List<int>();
        for (int i = 0; i < Math.Pow(10, n); i++)
        {
            ans.Add(i);
        }
        return ans.ToArray();
    }
}