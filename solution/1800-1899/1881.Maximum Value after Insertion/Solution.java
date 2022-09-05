class Solution {
    public String maxValue(String n, int x) {
        int i = 0;
        if (n.charAt(0) != '-') {
            for (; i < n.length() && n.charAt(i) - '0' >= x; ++i)
                ;
        } else {
            for (i = 1; i < n.length() && n.charAt(i) - '0' <= x; ++i)
                ;
        }
        return n.substring(0, i) + x + n.substring(i);
    }
}