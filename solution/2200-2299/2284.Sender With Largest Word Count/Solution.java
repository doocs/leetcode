class Solution {
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> cnt = new HashMap<>();
        int n = senders.length;
        for (int i = 0; i < n; ++i) {
            int v = 1;
            for (int j = 0; j < messages[i].length(); ++j) {
                if (messages[i].charAt(j) == ' ') {
                    ++v;
                }
            }
            cnt.merge(senders[i], v, Integer::sum);
        }
        String ans = senders[0];
        for (var e : cnt.entrySet()) {
            String sender = e.getKey();
            if (cnt.get(ans) < cnt.get(sender)
                || (cnt.get(ans) == cnt.get(sender) && ans.compareTo(sender) < 0)) {
                ans = sender;
            }
        }
        return ans;
    }
}