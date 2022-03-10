class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new LinkedList<>();
        q.offerLast(id);
        vis[id] = true;
        while (level-- > 0) {
            for (int i = q.size(); i > 0; --i) {
                int u = q.pollFirst();
                for(int v : friends[u]) {
                    if (!vis[v]) {
                        q.offerLast(v);
                        vis[v] = true;
                    }
                }
            }
        }
        Map<String, Integer> freq = new HashMap<>();
        while (!q.isEmpty()) {
            for (String w : watchedVideos.get(q.pollFirst())) {
                freq.put(w, freq.getOrDefault(w, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> t = new ArrayList<>(freq.entrySet());
        t.sort((a, b) -> {
            if (a.getValue() > b.getValue()) {
                return 1;
            }
            if (a.getValue() < b.getValue()) {
                return -1;
            }
            return a.getKey().compareTo(b.getKey());
        });
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> e : t) {
            ans.add(e.getKey());
        }
        return ans;
    }
}