public class Solution {
    public IList<long> MaximumEvenSplit(long finalSum) {
        IList<long> ans = new List<long>();
        if (finalSum % 2 == 1) {
            return ans;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            ans.Add(i);
            finalSum -= i;
        }
        ans[ans.Count - 1] += finalSum;
        return ans;
    }
}
