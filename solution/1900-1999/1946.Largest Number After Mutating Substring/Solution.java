class Solution {
    public String maximumNumber(String num, int[] change) {
        boolean find = false;
        char[] nums = num.toCharArray();
        for (int i = 0; i < num.length(); ++i) {
            int c = num.charAt(i) - '0';
            if (c < change[c]) {
                nums[i] = (char) ('0' + change[c]);
                find = true;
            } else if (find && c == change[c]) {
                continue;
            } else if (find) {
                break;
            }
        }
        return new String(nums);
    }
}