class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        List<Character>[] buckets = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i >= 0; --i) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; ++j) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}