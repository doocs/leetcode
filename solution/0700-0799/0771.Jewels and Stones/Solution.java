class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<>();
        for (char ch : jewels.toCharArray()) {
            jewelSet.add(ch);
        }
        int res = 0;
        for (char ch : stones.toCharArray()) {
            res += (jewelSet.contains(ch) ? 1 : 0);
        }
        return res;
    }
}