class Solution {
    public boolean oneEditAway(String first, String second) {
        int n1 = first.length(), n2 = second.length();
        int differ = Math.abs(n1 - n2);
        if (differ > 1) {
            return false;
        }
        if (n1 + n2 <= 2) {
            return true;
        }
        if (first.charAt(0) != second.charAt(0)) {
            if (n1 == n2) {
                return first.substring(1).equals(second.substring(1));
            } else {
                return first.substring(1).equals(second) || second.substring(1).equals(first);
            }
        } else {
            return oneEditAway(first.substring(1), second.substring(1));
        }
    }
}