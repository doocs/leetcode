public class NumberContainers {
    private Dictionary<int, int> d = new Dictionary<int, int>();
    private Dictionary<int, SortedSet<int>> g = new Dictionary<int, SortedSet<int>>();

    public NumberContainers() {
    }

    public void Change(int index, int number) {
        if (d.TryGetValue(index, out int oldNumber)) {
            g[oldNumber].Remove(index);
        }
        d[index] = number;
        if (!g.ContainsKey(number)) {
            g[number] = new SortedSet<int>();
        }
        g[number].Add(index);
    }

    public int Find(int number) {
        if (!g.ContainsKey(number) || g[number].Count == 0) {
            return -1;
        }
        return g[number].Min;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.Change(index, number);
 * int param_2 = obj.Find(number);
 */
