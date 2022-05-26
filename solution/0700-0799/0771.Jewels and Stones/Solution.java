class Solution {
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (char ch : J.toCharArray()) {
            set.add(ch);
        }
        int res = 0;
        for (char ch : S.toCharArray()) {
            res += (set.contains(ch) ? 1 : 0);
        }
        return res;
    }
}