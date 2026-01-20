class Solution {
    public List<Integer> goodIndices(String s) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String t = String.valueOf(i);
            int k = t.length();
            if (s.substring(i + 1 - k, i + 1).equals(t)) {
                ans.add(i);
            }
        }
        return ans;
    }
}
