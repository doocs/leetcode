class Solution {
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        for (char c : S.toCharArray()) {
            if (contains(J, c))
                res++;
        }
        return res;
    }

    public boolean contains(String s, char c) {
        for (char k : s.toCharArray()) {
            if (k == c)
                return true;
        }
        return false;
    }
}