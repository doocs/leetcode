using System;
using System.Linq;

class Range
{
    public static Range Empty = new Range(new int[0], 0, -1);

    public readonly int[] Numbers;
    public readonly int LeftIndex;
    public readonly int RightIndex;

    public int Count { get { return RightIndex - LeftIndex + 1; } }

    public int this[int index]
    {
        get
        {
            if (index >= Count)
            {
                throw new IndexOutOfRangeException();
            }
            return Numbers[LeftIndex + index];
        }
    }

    public Range(int[] numbers) : this(numbers, 0, numbers.Length - 1)
    {
    }

    public Range(int[] numbers, int leftIndex, int rightIndex)
    {
        Numbers = numbers;
        LeftIndex = leftIndex;
        RightIndex = rightIndex;
        if (RightIndex < LeftIndex) RightIndex = LeftIndex - 1;
    }

    public Range GetSubRange(int lowerBound, int upperBound)
    {
        if (lowerBound > upperBound) return Empty;
        var leftIndex = lowerBound == int.MinValue ? LeftIndex : Search(lowerBound);
        var rightIndex = upperBound == int.MaxValue ? RightIndex : Search(upperBound + 1) - 1;
        return new Range(Numbers, leftIndex, rightIndex);
    }

    private int Search(int target)
    {
        var l = 0;
        var r = Numbers.Length - 1;
        while (l < r)
        {
            var mid = (l + r) / 2;
            if (Numbers[mid] < target)
            {
                l = mid + 1;
            }
            else
            {
                r = mid;
            }
        }
        return Numbers[l] >= target ? l : l + 1;
    }
}

public class Solution {
    public double FindMedianSortedArrays(int[] nums1, int[] nums2)
    {
        var totalNumbers = nums1.Length + nums2.Length;
        var targetOrder1 = (totalNumbers + 1)/2;
        var targetOrder2 = (totalNumbers + 2)/2;
        var range1 = new Range(nums1);
        var range2 = new Range(nums2);
        var number1 = FindMedianSortedArrays(range1, range2, targetOrder1);
        var number2 = targetOrder1 == targetOrder2 ? number1 : FindMedianSortedArrays(range1, range2, targetOrder2);
        return ((double) number1 + number2)/2;
    }

    private int FindMedianSortedArrays(Range range1, Range range2, int targetOrder)
    {
        if (range1.Count == 0)
        {
            return range2[targetOrder - 1];
        }
        if (range2.Count == 0)
        {
            return range1[targetOrder - 1];
        }

        var midNumber = range1[(range1.Count - 1)/2];
        var midRanges = new[] { range1.GetSubRange(midNumber, midNumber), range2.GetSubRange(midNumber, midNumber) };
        var leftRanges = new[]
        {
            new Range(range1.Numbers, range1.LeftIndex, midRanges[0].LeftIndex - 1),
            new Range(range2.Numbers, range2.LeftIndex, midRanges[1].LeftIndex - 1)
        };
        var rightRanges = new[]
        {
            new Range(range1.Numbers, midRanges[0].RightIndex + 1, range1.RightIndex),
            new Range(range2.Numbers, midRanges[1].RightIndex + 1, range2.RightIndex)
        };

        var leftCount = leftRanges.Sum(r => r.Count);
        var midCount = midRanges.Sum(r => r.Count);
        var rightCount = rightRanges.Sum(r => r.Count);

        if (leftCount == 0 && rightCount == 0)
        {
            return midNumber;
        }
        if (leftCount >= targetOrder)
        {
            return FindMedianSortedArrays(leftRanges[0], leftRanges[1], targetOrder);
        }
        if (leftCount + midCount >= targetOrder)
        {
            return FindMedianSortedArrays(midRanges[0], midRanges[1], targetOrder - leftCount);
        }
        return FindMedianSortedArrays(rightRanges[0], rightRanges[1], targetOrder - leftCount - midCount);
    }
}