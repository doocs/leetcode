class Solution {
    public List<String> stringSequence(String target) {
        List<String> ans = new ArrayList<>();
        for (char c : target.toCharArray()) {
            String s = ans.isEmpty() ? "" : ans.get(ans.size() - 1);
            for (char a = 'a'; a <= c; ++a) {
                String t = s + a;
                ans.add(t);
            }
        }
        return ans;
    }
}
