class Solution {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[] dp = new int[n];
        int[] next = new int[n];
        Map<String, List<Integer>> strToIdxMap = new HashMap<>();
        int maxIdx = n;
        for (int i = n - 1; i >= 0; i--) {
            int prevIdx = n;
            char[] word = words[i].toCharArray();
            for (int j = 0; j < word.length; j++) {
                // convert word to pattern with '*'.
                char temp = word[j];
                word[j] = '*';
                String curr = new String(word);

                // search matches and update dp.
                List<Integer> prevList = strToIdxMap.getOrDefault(curr, List.of());
                for (int prev : prevList) {
                    if (groups[prev] == groups[i] || dp[prev] < dp[i]) {
                        continue;
                    }
                    dp[i] = dp[prev] + 1;
                    prevIdx = prev;
                }

                // append current pattern to dictionary.
                strToIdxMap.computeIfAbsent(curr, k -> new ArrayList<>()).add(i);

                // restore pattern to orignal word.
                word[j] = temp;
            }
            if (maxIdx >= n || dp[i] > dp[maxIdx]) {
                maxIdx = i;
            }
            next[i] = prevIdx;
        }
        int curr = maxIdx;
        List<String> ans = new ArrayList<>();
        while (curr < n) {
            ans.add(words[curr]);
            curr = next[curr];
        }
        return ans;
    }
}