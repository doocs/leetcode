class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for (String v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        for (String v : arr) {
            if (counter.get(v) == 1) {
                --k;
                if (k == 0) {
                    return v;
                }
            }
        }
        return "";
    }
}