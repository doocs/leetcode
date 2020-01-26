public class Solution {
    public int CanCompleteCircuit(int[] gas, int[] cost) {
        if (gas.Length == 0) return -1;
        var startIndex = 0;
        var i = 0;
        var gasLeft = 0;
        while (true)
        {
            gasLeft += gas[i] - cost[i];
            ++i;
            if (i >= gas.Length) i = 0;
            if (gasLeft < 0)
            {
                if (startIndex >= i)
                {
                    return -1;
                }
                startIndex = i;
                gasLeft = 0;
            }
            else
            {
                if (startIndex == i)
                {
                    return startIndex;
                }
            }
        }
    }
}