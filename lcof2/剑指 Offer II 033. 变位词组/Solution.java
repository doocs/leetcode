class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> chars = new HashMap<>();
        for (String s : strs) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            String k = new String(t);
            if (!chars.containsKey(k)) {
                chars.put(k, new ArrayList<>());
            }
            chars.get(k).add(s);
        }
        return new ArrayList<>(chars.values());
    }
}