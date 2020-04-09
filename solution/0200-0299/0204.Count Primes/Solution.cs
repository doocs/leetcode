public class Solution {
    public int CountPrimes(int n) {
        var count = 0;
        var notPrime = new bool[n];
        for (var i = 2; i < n; ++i)
        {
            if (!notPrime[i])
            {
                ++count;
                for (var j = i + i; j < n; j += i)
                {
                    notPrime[j] = true;
                }
            }
        }
        return count;
    }
}