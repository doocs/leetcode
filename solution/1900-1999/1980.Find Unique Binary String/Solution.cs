public class Solution {
    public string FindDifferentBinaryString(string[] nums) {
        int mask = 0;
        foreach (var x in nums) {
            int cnt = x.Count(c => c == '1');
            mask |= 1 << cnt;
        }
        int i = 0;
        while ((mask >> i & 1) == 1) {
            i++;
        }
        return string.Format("{0}{1}", new string('1', i), new string('0', nums.Length - i));
    }
}
