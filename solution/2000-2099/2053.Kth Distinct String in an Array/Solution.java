class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : arr) {
            cnt.merge(s, 1, Integer::sum);
        }
        for (String s : arr) {
            if (cnt.get(s) == 1 && --k == 0) {
                return s;
            }
        }
        return "";
    }
}
