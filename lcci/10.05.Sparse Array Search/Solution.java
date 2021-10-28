class Solution {
    public int findString(String[] words, String s) {
        int left = 0, right = words.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            while (left < mid && "".equals(words[mid])) {
                --mid;
            }
            if (s.compareTo(words[mid]) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return s.equals(words[left]) ? left : -1;
    }
}