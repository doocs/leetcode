class Solution {
    public int mirrorFrequency(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }

        int ans = 0;
        Set<Character> vis = new HashSet<>();

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            char c = entry.getKey();
            int v = entry.getValue();

            char m;
            if (Character.isLetter(c)) {
                m = (char) ('a' + 25 - (c - 'a'));
            } else {
                m = (char) ('0' + (9 - (c - '0')));
            }

            if (vis.contains(m)) {
                continue;
            }
            vis.add(c);

            int mv = freq.getOrDefault(m, 0);
            ans += Math.abs(v - mv);
        }

        return ans;
    }
}
