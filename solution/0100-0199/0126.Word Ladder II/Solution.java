class Solution {
    private boolean isConnected = false;
    private Map<String, List<String>> hs;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        hs = new HashMap<>(16);
        List<List<String>> result = new ArrayList<>();
        if (!wordList.contains(endWord))
            return result;
        HashSet<String> dict = new HashSet<>(wordList);
        Set<String> fwd = new HashSet<>();
        fwd.add(beginWord);
        Set<String> bwd = new HashSet<>();
        bwd.add(endWord);
        bfs(fwd, bwd, dict, false);
        if (!isConnected) return result;
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        dfs(result, temp, beginWord, endWord);
        return result;
    }

    private void bfs(Set<String> forward, Set<String> backward, Set<String> dict, boolean swap) {
        if (forward.isEmpty() || backward.isEmpty()) return;
        if (forward.size() > backward.size()) {
            bfs(backward, forward, dict, !swap);
            return;
        }
        dict.removeAll(forward);
        dict.removeAll(backward);
        Set<String> set3 = new HashSet<>();
        for (String str : forward)
            for (int i = 0; i < str.length(); i++) {
                char[] ary = str.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    ary[i] = j;
                    String temp = new String(ary);
                    if (!backward.contains(temp) && !dict.contains(temp)) continue;
                    String key = !swap ? str : temp;
                    String val = !swap ? temp : str;
                    if (backward.contains(temp)) {
                        hs.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
                        isConnected = true;
                    }
                    if (!isConnected && dict.contains(temp)) {
                        hs.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
                        set3.add(temp);
                    }
                }
            }
        if (!isConnected) bfs(set3, backward, dict, swap);
    }

    private void dfs(List<List<String>> result, List<String> temp, String start, String end) {
        if (start.equals(end)) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (!hs.containsKey(start)) return;
        for (String s : hs.get(start)) {
            temp.add(s);
            dfs(result, temp, s, end);
            temp.remove(temp.size() - 1);
        }
    }
}