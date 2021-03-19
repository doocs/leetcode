class Solution {
    public String getHint(String secret, String guess) {
        int aCnt = 0, bCnt = 0;
        Map<Character, Integer> nums1 = new HashMap<>();
        Map<Character, Integer> nums2 = new HashMap<>();
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++aCnt;
            } else {
                nums1.put(secret.charAt(i), nums1.getOrDefault(secret.charAt(i), 0) + 1);
                nums2.put(guess.charAt(i), nums2.getOrDefault(guess.charAt(i), 0) + 1);
            }
        }
        
        for (Map.Entry<Character, Integer> entry : nums1.entrySet()) {
            if (nums2.containsKey(entry.getKey())) {
                bCnt += Math.min(entry.getValue(), nums2.get(entry.getKey()));
            }
        }
        return String.format("%dA%dB", aCnt, bCnt);
    }
}