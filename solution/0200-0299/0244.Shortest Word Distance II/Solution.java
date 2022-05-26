class WordDistance {

    Map<String, List<Integer>> map = new HashMap<>();

    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            List<Integer> indexList = map.get(words[i]) == null ? new ArrayList<>() : map.get(words[i]);
            indexList.add(i);
            map.put(words[i], indexList);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int ans = Integer.MAX_VALUE;
        for (int l1 : list1) {
            for (int l2 : list2) {
                ans = Math.min(ans, Math.abs(l1 - l2));
            }
        }
        return ans;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */