class Solution {
    public int numDifferentIntegers(String word) {
        String[] nums = word.split("[a-z]+");
        Set<String> numSet = new HashSet<>();
        for (String num : nums) {
            if ("".equals(num)) {
                continue;
            }
            int j = 0;
            while (j < num.length() - 1 && num.charAt(j) == '0') {
                ++j;
            }
            numSet.add(num.substring(j));
        }
        return numSet.size();
    }
}