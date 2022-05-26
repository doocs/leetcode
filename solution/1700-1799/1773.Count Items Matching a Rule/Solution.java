class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        for (List<String> item : items) {
            String t = item.get(0), c = item.get(1), n = item.get(2);
            if ("type".equals(ruleKey) && t.equals(ruleValue)) {
                ++count;
            } else if ("color".equals(ruleKey) && c.equals(ruleValue)) {
                ++count;
            } else if ("name".equals(ruleKey) && n.equals(ruleValue)) {
                ++count;
            }
        }
        return count;
    }
}