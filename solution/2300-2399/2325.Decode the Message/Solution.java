class Solution {
    public String decodeMessage(String key, String message) {
        Map<Character, Character> d = new HashMap<>();
        String lowcase = "abcdefghijklmnopqrstuvwxyz";
        d.put(' ', ' ');
        int i = 0;
        for (char c : key.toCharArray()) {
            if (d.containsKey(c)) {
                continue;
            }
            d.put(c, lowcase.charAt(i++));
        }
        StringBuilder ans = new StringBuilder();
        for (char c : message.toCharArray()) {
            ans.append(d.get(c));
        }
        return ans.toString();
    }
}