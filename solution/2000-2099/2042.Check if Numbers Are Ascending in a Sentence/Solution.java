class Solution {
    public boolean areNumbersAscending(String s) {
        int pre = 0;
        for (var t : s.split(" ")) {
            if (t.charAt(0) <= '9') {
                int cur = Integer.parseInt(t);
                if (pre >= cur) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
}