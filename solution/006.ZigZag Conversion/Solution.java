class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder result = new StringBuilder();
        int group = 2 * numRows - 2;
        for (int i = 1; i <= numRows; i++) {
            int interval = 2 * numRows - 2 * i;
            if (i == numRows) interval = 2 * numRows - 2;
            int index = i;
            while (index <= s.length()) {
                result.append(s.charAt(index - 1));
                index += interval;
                interval = group - interval;
                if (interval == 0) interval = group;
            }
        }
        return result.toString();
    }
}
