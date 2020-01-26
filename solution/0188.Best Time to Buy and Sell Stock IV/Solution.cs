using System;
using System.Collections.Generic;
using System.Linq;

public class Comparer : IComparer<LinkedListNode<Tuple<int, int>>>
{
    public int Compare(LinkedListNode<Tuple<int, int>> left, LinkedListNode<Tuple<int, int>> right)
    {
        var result = left.Value.Item1.CompareTo(right.Value.Item1);
        if (result == 0 && left != right)
        {
            return left.Value.Item2.CompareTo(right.Value.Item2);
        }
        return result;
    }
}

public class Solution {
    private SortedSet<LinkedListNode<Tuple<int, int>>> heap = new SortedSet<LinkedListNode<Tuple<int, int>>>(new Comparer());
    private LinkedList<Tuple<int, int>> list = new LinkedList<Tuple<int, int>>();
    
    private int lable = 0;
    private Tuple<int, int> CreateNodeValue(int value)
    {
        return Tuple.Create(value, lable++);
    }
    
    public int MaxProfitStupid(int k, int[] prices)
    {
        var f = new int[prices.Length + 1, k + 1];
        for (var i = 2; i <= prices.Length; ++i)
        {
            for (var kk = 1; kk <= k; ++kk)
            {
                for (var j = 0; j + 1 < i; ++j)
                {
                    f[i, kk] = Math.Max(f[i, kk], Math.Max(Math.Max(f[i, kk - 1], f[i - 1, kk]), f[j, kk - 1] + prices[i - 1] - prices[j]));
                }
            }
        }
        return f[prices.Length, k];
    }
    
    public int MaxProfit(int k, int[] prices) {
        var result = 0;
        var i = 0;
        var profitCount = 0;
        var tempList = new List<Tuple<int, bool>>();
        while (i + 1 < prices.Length)
        {
            var highIndex = i;
            while (i + 1 < prices.Length && prices[i] >= prices[i + 1])
            {
                ++i;
            }
            var lowIndex = i;
            while (i + 1 < prices.Length && prices[i] <= prices[i + 1])
            {
                ++i;
            }
            var highIndex2 = i;
            
            if (highIndex != lowIndex)
            {
                tempList.Add(Tuple.Create(prices[highIndex] - prices[lowIndex], false));
            }
            if (lowIndex != highIndex2)
            {
                tempList.Add(Tuple.Create(prices[highIndex2] - prices[lowIndex], true));
                result += prices[highIndex2] - prices[lowIndex];
                ++profitCount;
            }
        }
        
        // Trim gaps
        if (tempList.Any() && tempList.First().Item2 == false)
        {
            tempList.RemoveAt(0);
        }
        if (tempList.Any() && tempList.Last().Item2 == false)
        {
            tempList.RemoveAt(tempList.Count - 1);
        }
        
        foreach (var temp in tempList)
        {
            var node = list.AddLast(CreateNodeValue(temp.Item1));
            heap.Add(node);
        }
        //Console.WriteLine("profitCount: {0}. tempList size: {1}. Heap size: {2}.", profitCount, tempList.Count, heap.Sum(item =>item.Value.Count));
                
        for (var j = 0; j < profitCount - k; ++j)
        {
            var node = heap.Min;
            result -= node.Value.Item1;
            //Console.WriteLine(node.Value);
            var previous = node.Previous;
            var next = node.Next;
            var newValue = (previous == null ? 0 : previous.Value.Item1) + (next == null ? 0 : next.Value.Item1) - node.Value.Item1;
            if (previous != null)
            {
                heap.Remove(previous);
                list.Remove(previous);
            }
            if (next != null)
            {
                heap.Remove(next);
                list.Remove(next);
            }
            if (previous != null && next != null)
            { 
                var newNode = list.AddBefore(node, CreateNodeValue(newValue));
                heap.Add(newNode);
            }
            heap.Remove(node);
            list.Remove(node);
        }

        return result;
    }
} 