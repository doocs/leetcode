public class Solution {
    public IList<int> MajorityElement(int[] nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        foreach (int m in nums)
        {
            if (m == m1)
            {
                ++n1;
            }
            else if (m == m2)
            {
                ++n2;
            }
            else if (n1 == 0)
            {
                m1 = m;
                ++n1;
            }
            else if (n2 == 0)
            {
                m2 = m;
                ++n2;
            }
            else
            {
                --n1;
                --n2;
            }
        }
        var ans = new List<int>();
        ans.Add(m1);
        ans.Add(m2);
        return ans.Where(m => nums.Count(n => n == m) > nums.Length / 3).ToList();
    }
}