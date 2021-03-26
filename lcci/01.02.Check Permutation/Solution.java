class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 != n2) {
            return false;
        }
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < n1; ++i) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            counter.put(c1, counter.getOrDefault(c1, 0) + 1);
            counter.put(c2, counter.getOrDefault(c2, 0) - 1);
        }
        for (int val : counter.values()) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }
}