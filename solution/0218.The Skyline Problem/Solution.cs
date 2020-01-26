// https://leetcode.com/problems/the-skyline-problem/

// TODO: Time Limit Exceeded

using System.Collections.Generic;
using System.Linq;

public partial class Solution
{
    public IList<int[]> GetSkyline(int[,] buildings)
    {
        if (buildings.Length == 0) return new List<int[]>();
        return GetSkyline_Internal(GetSkyline_ToBuildings(buildings)).ToList();
    }

    private IEnumerable<GetSkyline_Building> GetSkyline_ToBuildings(int[,] buildings)
    {
        var rightMax = 0;
        var length = buildings.GetLength(0);
        for (var i = 0; i < length; ++i)
        {
            if (buildings[i, 1] > rightMax)
            {
                rightMax = buildings[i, 1];
            }
        }
        if (length > 0)
        {
            yield return new GetSkyline_Building { Left = buildings[0, 0], Right = rightMax, Height = 0, Index = -1 };
        }
        for (var i = 0; i < length; ++i)
        {
            var building = new GetSkyline_Building { Left = buildings[i, 0], Right = buildings[i, 1], Height = buildings[i, 2], Index = i };
            yield return building;
        }
    }

    private IEnumerable<int[]> GetSkyline_Internal(IEnumerable<GetSkyline_Building> buildings)
    {
        GetSkyline_Building leftUp = null;
        var orderedByRight = new SortedList<GetSkyline_Building, object>(new GetSkyline_RightComparer());
        var orderedByHeight = new SortedList<GetSkyline_Building, object>(new GetSkyline_HeightComparer());
        var lastOutputX = -1;
        var lastOutputY = -1;
        foreach (var current in buildings)
        {
            while (orderedByRight.Count > 0)
            {
                var smallRight = orderedByRight.First().Key;
                if (smallRight.Right < current.Left)
                {
                    orderedByRight.Remove(smallRight);
                    orderedByHeight.Remove(smallRight);
                    var largeHeight = orderedByHeight.Last().Key;
                    if (smallRight.Height > largeHeight.Height && lastOutputX < smallRight.Right && lastOutputY != largeHeight.Height)
                    {
                        yield return new[] { smallRight.Right, largeHeight.Height };
                        lastOutputX = smallRight.Right;
                        lastOutputY = largeHeight.Height;
                        if (leftUp == smallRight)
                        {
                            leftUp = null;
                        }
                    }
                }
                else
                {
                    break;
                }
            }

            if (leftUp == null)
            {
                leftUp = current;
            }
            else if (leftUp.Left == current.Left)
            {
                if (leftUp.Height < current.Height)
                {
                    leftUp = current;
                }
            }
            else // leftUp.Left < current.Left
            {
                if (lastOutputX < leftUp.Left && lastOutputY != leftUp.Height)
                {
                    yield return new[] { leftUp.Left, leftUp.Height };
                    lastOutputX = leftUp.Left;
                    lastOutputY = leftUp.Height;
                }
                if (leftUp.Height < current.Height)
                {
                    leftUp = current;
                }
            }

            orderedByRight.Add(current, null);
            orderedByHeight.Add(current, null);
        }

        if (leftUp != null && lastOutputX < leftUp.Left && lastOutputY != leftUp.Height)
        {
            yield return new[] { leftUp.Left, leftUp.Height };
            lastOutputX = leftUp.Left;
            lastOutputY = leftUp.Height;
        }

        while (orderedByRight.Count > 0)
        {
            var smallRight = orderedByRight.First().Key;
            orderedByRight.Remove(smallRight);
            orderedByHeight.Remove(smallRight);
            if (lastOutputX < smallRight.Right)
            {
                if (orderedByRight.Count == 0 && lastOutputY != 0)
                {
                    yield return new[] { smallRight.Right, 0 };
                    lastOutputX = smallRight.Right;
                    lastOutputY = 0;
                }
                else
                {
                    var largeHeight = orderedByHeight.Last().Key;
                    if (smallRight.Height > largeHeight.Height && smallRight.Right < largeHeight.Right)
                    {
                        if (lastOutputY != largeHeight.Height)
                        {
                            yield return new[] { smallRight.Right, largeHeight.Height };
                            lastOutputX = smallRight.Right;
                            lastOutputY = largeHeight.Height;
                        }
                    }
                }
            }
        }
    }

    class GetSkyline_Building
    {
        public int Left;
        public int Right;
        public int Height;
        public int Index;

        public override string ToString()
        {
            return string.Format("[{0} {1} {2}]", Left, Right, Height);
        }
    }

    class GetSkyline_RightComparer : IComparer<GetSkyline_Building>
    {
        public int Compare(GetSkyline_Building x, GetSkyline_Building y)
        {
            var z = x.Right.CompareTo(y.Right);
            if (z != 0) return z;
            return x.Index.CompareTo(y.Index);
        }
    }

    class GetSkyline_HeightComparer : IComparer<GetSkyline_Building>
    {
        public int Compare(GetSkyline_Building x, GetSkyline_Building y)
        {
            var z = x.Height.CompareTo(y.Height);
            if (z != 0) return z;
            return x.Index.CompareTo(y.Index);
        }
    }
}
