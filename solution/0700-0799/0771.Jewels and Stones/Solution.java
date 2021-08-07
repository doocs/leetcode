class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> s = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            s.add(c);
        }
        int res = 0;
        for (char c : stones.toCharArray()) {
            res += (s.contains(c) ? 1 : 0);
        }
        return res;
    }
}