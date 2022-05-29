class Solution {
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> cnt = new HashMap<>();
        int n = senders.length;
        for (int i = 0; i < n; ++i) {
            cnt.put(senders[i], cnt.getOrDefault(senders[i], 0) + messages[i].split(" ").length);
        }
        String ans = senders[0];
        for (Map.Entry<String, Integer> e : cnt.entrySet()) {
            String u = e.getKey();
            int v = e.getValue();
            if (v > cnt.get(ans) || (v == cnt.get(ans) && ans.compareTo(u) < 0)) {
                ans = u;
            }
        }
        return ans;
    }
}