public class Solution {
    public bool IsSelfCrossing(int[] x) {
        for (var i = 3; i < x.Length; ++i)
        {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) return true;
            if (i > 3 && x[i] + x[i - 4] >= x[i - 2])
            {
                if (x[i - 1] == x[i - 3]) return true;
                if (i > 4 && x[i - 2] >= x[i - 4] && x[i - 1] <= x[i - 3] && x[i - 1] + x[i - 5] >= x[i - 3]) return true;
            }
        }
        return false;
    }
}