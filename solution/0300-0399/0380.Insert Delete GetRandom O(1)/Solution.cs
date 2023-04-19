public class RandomizedSet {
    private Dictionary<int, int> d = new Dictionary<int, int>();
    private List<int> q = new List<int>();

    public RandomizedSet() {

    }
    
    public bool Insert(int val) {
        if (d.ContainsKey(val)) {
            return false;
        }
        d.Add(val, q.Count);
        q.Add(val);
        return true;
    }
    
    public bool Remove(int val) {
        if (!d.ContainsKey(val)) {
            return false;
        }
        int i = d[val];
        d[q[q.Count - 1]] = i;
        q[i] = q[q.Count - 1];
        q.RemoveAt(q.Count - 1);
        d.Remove(val);
        return true;
    }
    
    public int GetRandom() {
        return q[new Random().Next(0, q.Count)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * bool param_1 = obj.Insert(val);
 * bool param_2 = obj.Remove(val);
 * int param_3 = obj.GetRandom();
 */