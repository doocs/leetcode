class Solution {
    public String maxValue(String n, int x) {
        boolean negative = n.charAt(0) == '-';
        StringBuilder res = new StringBuilder();
        int i = 0;
        if (negative) {
            ++i;
            res.append("-");
        }
        boolean find = false;
        for (; i < n.length(); ++i) {
            int num = n.charAt(i) - '0';
            if ((negative && x < num) || (!negative && x > num)) {
                res.append(x);
                find = true;
                break;
            }
            res.append(n.charAt(i));
        }
        res.append(find ? n.substring(i) : x);
        return res.toString();
    }
}