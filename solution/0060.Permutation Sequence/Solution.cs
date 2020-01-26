using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Solution {
    public string GetPermutation(int n, int k) {
        --k;
        var factorial = Enumerable.Range(0, n).Select(i => Enumerable.Range(1, i).Aggregate(1, (agg, x) => agg * x)).ToArray();
        var numbers = new SortedSet<int>(Enumerable.Range(1, n));
        var sb = new StringBuilder();
        while (sb.Length < n)
        {
            var f = factorial[numbers.Count - 1];
            var index = k / f;
            var number = numbers.ElementAt(index);
            sb.Append(number);
            numbers.Remove(number);
            k %= f;
        }
        return sb.ToString();
    }
}
