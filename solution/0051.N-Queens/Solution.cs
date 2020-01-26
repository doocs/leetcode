using System.Collections.Generic;
using System.Text;

public class Solution {
    private IList<IList<string>> results = new List<IList<string>>();
    private int n;

    public IList<IList<string>> SolveNQueens(int n) {
        this.n = n;
        Search(new List<int>(), 0, 0, 0);
        return results;
    }

    private void Search(IList<int> state, int left, int right, int vertical)
    {
        if (state.Count == n)
        {
            Print(state);
            return;
        }
        int available = ~(left | right | vertical) & ((1 << n) - 1);
        while (available != 0)
        {
            int x = available & -available;
            state.Add(x);
            Search(state, (left | x ) << 1, (right | x ) >> 1, vertical | x);
            state.RemoveAt(state.Count - 1);
            available &= ~x;
        }
    }

    private void Print(IList<int> state)
    {
        var result = new List<string>();
        var sb = new StringBuilder(n);
        foreach (var s in state)
        {
            var x = s;
            for (var i = 0; i < n; ++i)
            {
                sb.Append((x & 1) != 0 ? 'Q': '.');
                x >>= 1;
            }
            result.Add(sb.ToString());
            sb.Clear();
        }
        results.Add(result);
    }
}