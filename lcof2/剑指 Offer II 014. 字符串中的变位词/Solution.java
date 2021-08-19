class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        int[] window = new int[26];
        for (int i = 0; i < n1; i++) {
            window[s1.charAt(i) - 'a']++;
            window[s2.charAt(i) - 'a']--;
        }
        if (check(window)) {
            return true;
        }
        for (int i = n1; i < n2; i++) {
            window[s2.charAt(i) - 'a']--;
            window[s2.charAt(i - n1) - 'a']++;
            if (check(window)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] window) {
        return Arrays.stream(window).allMatch(cnt -> cnt == 0);
    }
}
