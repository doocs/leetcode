using System;
using System.Collections.Generic;

public class Comparer : IComparer<Tuple<double, int>>
{
    public int Compare(Tuple<double, int> x, Tuple<double, int> y)
    {
        var result = x.Item1.CompareTo(y.Item1);
        if (result != 0)
        {
            return result;
        }
        return x.Item2.CompareTo(y.Item2);
    }
}

public class MedianFinder {

    private SortedSet<Tuple<double, int>> smallerHeap = new SortedSet<Tuple<double, int>>();
    private SortedSet<Tuple<double, int>> biggerHeap = new SortedSet<Tuple<double, int>>();

    private int index;

    public void AddNum(double num) {
        if (smallerHeap.Count == 0 || smallerHeap.Max.Item1 >= num)
        {
            smallerHeap.Add(Tuple.Create(num, index++));
        }
        else
        {
            biggerHeap.Add(Tuple.Create(num, index++));
        }

        if (smallerHeap.Count == biggerHeap.Count + 2)
        {
            biggerHeap.Add(smallerHeap.Max);
            smallerHeap.Remove(smallerHeap.Max);
        }
        else if (biggerHeap.Count == smallerHeap.Count + 2)
        {
            smallerHeap.Add(biggerHeap.Min);
            biggerHeap.Remove(biggerHeap.Min);
        }
    }

    public double FindMedian() {
        if (smallerHeap.Count == biggerHeap.Count)
        {
            return (smallerHeap.Max.Item1 + biggerHeap.Min.Item1) / 2;
        }
        else if (smallerHeap.Count < biggerHeap.Count)
        {
            return biggerHeap.Min.Item1;
        }
        else
        {
            return smallerHeap.Max.Item1;
        }
    }
}