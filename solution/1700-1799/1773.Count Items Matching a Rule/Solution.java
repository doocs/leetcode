class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        char x = ruleKey.charAt(0);
        int i = x == 't' ? 0 : (x == 'c' ? 1 : 2);
        int ans = 0;
        for (var v : items) {
            if (v.get(i).equals(ruleValue)) {
                ++ans;
            }
        }
        return ans;
    }
}