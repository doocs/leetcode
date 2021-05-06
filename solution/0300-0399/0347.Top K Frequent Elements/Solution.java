class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }
        int[] res = new int[k];
        for (int i = nums.length; i >= 0 && k > 0; --i) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    if (k <= 0) {
                        break;
                    }
                    res[--k] = num;
                }
            }
        }
        return res;
    }
}