class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        if (num.length() == 1) {
            return "1".equals(num) || "8".equals(num) || "0".equals(num);
        }
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                return false;
            }
            if (chars[chars.length - 1 - i] != map.get(chars[i])) {
                return false;
            }
        }
        return true;
    }
}