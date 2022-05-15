class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        String prev = "";
        for (String w : words) {
            char[] cs = w.toCharArray();
            Arrays.sort(cs);
            String t = String.valueOf(cs);
            if (!t.equals(prev)) {
                ans.add(w);
            }
            prev = t;
        }
        return ans;
    }
}