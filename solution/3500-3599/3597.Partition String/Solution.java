class Solution {
    public List<String> partitionString(String s) {
        Set<String> vis = new HashSet<>();
        List<String> ans = new ArrayList<>();
        String t = "";
        for (char c : s.toCharArray()) {
            t += c;
            if (vis.add(t)) {
                ans.add(t);
                t = "";
            }
        }
        return ans;
    }
}