// https://leetcode.com/problems/max-points-on-a-line/

using System.Collections.Generic;

public class Solution
{
    public int MaxPoints(Point[] points)
    {
        var answer = 0;
        for (var i = 0; i < points.Length; ++i)
        {
            var ratios = new Dictionary<double, int>();
            var verticalRatio = 0;
            var samePoint = 0;
            var max = 0;
            for (var j = i + 1; j < points.Length; ++j)
            {
                if (points[i].x == points[j].x && points[i].y == points[j].y)
                {
                    ++samePoint;
                }
                else
                {
                    var ratio = ((double)points[j].y - (double)points[i].y) / ((double)points[j].x - (double)points[i].x);
                    if (double.IsInfinity(ratio))
                    {
                        ++verticalRatio;
                        if (max < verticalRatio)
                        {
                            max = verticalRatio;
                        }
                    }
                    else
                    {
                        if (ratios.ContainsKey(ratio))
                        {
                            ++ratios[ratio];
                            if (max < ratios[ratio])
                            {
                                max = ratios[ratio];
                            }
                        }
                        else
                        {
                            ratios[ratio] = 1;
                            if (max < 1)
                            {
                                max = 1;
                            }
                        }
                    }
                }
            }

            if (1 + samePoint + max > answer)
            {
                answer = 1 + samePoint + max;
            }
        }

        return answer;
    }
}