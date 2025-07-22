public class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Dictionary<int, int> cnt = new Dictionary<int, int>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        foreach (int x in nums2) {
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
        }
    }

    public void Add(int index, int val) {
        int oldVal = nums2[index];
        if (cnt.TryGetValue(oldVal, out int oldCount)) {
            if (oldCount == 1) {
                cnt.Remove(oldVal);
            } else {
                cnt[oldVal] = oldCount - 1;
            }
        }
        nums2[index] += val;
        int newVal = nums2[index];
        if (cnt.TryGetValue(newVal, out int newCount)) {
            cnt[newVal] = newCount + 1;
        } else {
            cnt[newVal] = 1;
        }
    }

    public int Count(int tot) {
        int ans = 0;
        foreach (int x in nums1) {
            int target = tot - x;
            if (cnt.TryGetValue(target, out int count)) {
                ans += count;
            }
        }
        return ans;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.Add(index,val);
 * int param_2 = obj.Count(tot);
 */
