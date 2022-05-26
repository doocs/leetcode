public class NumArray {
    private int[] sums;
    private int numsCount;
    private int sumsCount;

    public NumArray(int[] nums) {
        numsCount = nums.Length;
        sumsCount = 1;
        var x = numsCount;
        while (x > 1)
        {
            x /= 2;
            sumsCount *= 2;
        }
        sumsCount = sumsCount * 2 - 1 + numsCount;
        sums = new int[sumsCount + 1];

        for (var i = sumsCount; i > 0; --i)
        {
            if (i - sumsCount + numsCount - 1 >= 0)
            {
                sums[i] = nums[i - sumsCount + numsCount - 1];
            }
            else
            {
                var l = i * 2;
                var r = l + 1;
                if (l <= sumsCount)
                {
                    sums[i] += sums[l];
                    if (r <= sumsCount)
                    {
                        sums[i] += sums[r];
                    }
                }
            }
        }
        //System.Console.WriteLine(Newtonsoft.Json.JsonConvert.SerializeObject(sums));
    }

    public void Update(int i, int val) {
        var j = sumsCount - numsCount + i + 1;
        sums[j] = val;
        for (j /= 2; j > 0; j /= 2)
        {
            var l = j * 2;
            var r = l + 1;
            sums[j] = sums[l];
            if (r <= sumsCount)
            {
                sums[j] += sums[r];
            }
        }
        //System.Console.WriteLine(Newtonsoft.Json.JsonConvert.SerializeObject(sums));
    }

    public int SumRange(int i, int j) {
        return SumFromStart(j) - SumFromStart(i - 1);
    }

    private int SumFromStart(int i)
    {
        if (i < 0) return 0;
        var j = sumsCount - numsCount + i + 1;
        var sum = sums[j];
        for (; j / 2 > 0; j /= 2)
        {
            if (j % 2 != 0)
            {
                sum += sums[j - 1];
            }
        }
        return sum;
    }
}