// https://leetcode.com/problems/max-points-on-a-line/

using System.Collections.Generic;

public class Solution
{
    class Slope {
        private int _x;
        private int _y;

        public Slope(int x, int y) {
            if (x == 0 && y == 0) {
                throw new ArgumentException("Invalid slope.");
            }
            if (x == 0) {
                _x = 0;
                _y = 1;
                return;
            }
            if (y == 0) {
                _x = 1;
                _y = 0;
                return;
            }

            bool negative = x < 0 && y > 0 || x > 0 && y < 0;
            x = Math.Abs(x);
            y = Math.Abs(y);
            int tx = x;
            int ty = y;
            int tz = tx % ty;
            while (tz > 0) {
                tx = ty;
                ty = tz;
                tz = tx % ty;
            }

            _x = x / ty;
            _y = (negative ? -1 : 1) * (y / ty);
        }

        public override bool Equals(Object obj)
        {
            if (obj == null || !(obj is Slope)) {
                return false;
            } else {
                return _x == ((Slope) obj)._x && _y == ((Slope) obj)._y;
            }
        }

        public override int GetHashCode()
        {
            return _x.GetHashCode() ^ _y.GetHashCode();
        }
    }

    public int MaxPoints(int[][] points)
    {
        var answer = 0;
        for (var i = 0; i < points.Length; ++i)
        {
            var slopes = new Dictionary<Slope, int>();
            var samePoint = 0;
            var max = 0;
            for (var j = i + 1; j < points.Length; ++j)
            {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1])
                {
                    ++samePoint;
                }
                else
                {
                    var slope = new Slope(points[j][0] - points[i][0], points[j][1] - points[i][1]);
                    if (slopes.ContainsKey(slope))
                    {
                        ++slopes[slope];
                        if (max < slopes[slope])
                        {
                            max = slopes[slope];
                        }
                    }
                    else
                    {
                        slopes[slope] = 1;
                        if (max < 1)
                        {
                            max = 1;
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