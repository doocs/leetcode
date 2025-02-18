public class RangeFreqQuery {
    private Dictionary<int, List<int>> g;

    public RangeFreqQuery(int[] arr) {
        g = new Dictionary<int, List<int>>();
        for (int i = 0; i < arr.Length; ++i) {
            if (!g.ContainsKey(arr[i])) {
                g[arr[i]] = new List<int>();
            }
            g[arr[i]].Add(i);
        }
    }

    public int Query(int left, int right, int value) {
        if (g.ContainsKey(value)) {
            var idx = g[value];
            int l = idx.BinarySearch(left);
            int r = idx.BinarySearch(right + 1);
            l = l < 0 ? -l - 1 : l;
            r = r < 0 ? -r - 1 : r;
            return r - l;
        }
        return 0;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.Query(left, right, value);
 */
