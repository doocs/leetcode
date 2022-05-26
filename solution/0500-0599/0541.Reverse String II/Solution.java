class Solution {
    public String reverseStr(String s, int k) {
        if (k < 2) return s;
        StringBuilder sb = new StringBuilder();
        int length = s.length(), index = 0;
        while (index < length) {
            if (index + 2 * k <= length) {
                sb.append(reverse(s, index, index + k));
                sb.append(s.substring(index + k, index + 2 * k));
                index += 2 * k;
            } else if (index + k <= length){
                sb.append(reverse(s, index, index + k));
                sb.append(s.substring(index + k));
                break;
            } else {
                sb.append(reverse(s, index, length));
                break;
            }
        }
        return sb.toString();
    }

    private StringBuffer reverse(String s, int index, int end) {
        return new StringBuffer(s.substring(index, end)).reverse();
    }
}