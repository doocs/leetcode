class FileSharing {
    private int chunks;
    private int cur;
    private TreeSet<Integer> reused;
    private TreeMap<Integer, Set<Integer>> userChunks;

    public FileSharing(int m) {
        cur = 0;
        chunks = m;
        reused = new TreeSet<>();
        userChunks = new TreeMap<>();
    }

    public int join(List<Integer> ownedChunks) {
        int userID;
        if (reused.isEmpty()) {
            ++cur;
            userID = cur;
        } else {
            userID = reused.pollFirst();
        }
        userChunks.put(userID, new HashSet<>(ownedChunks));
        return userID;
    }

    public void leave(int userID) {
        reused.add(userID);
        userChunks.remove(userID);
    }

    public List<Integer> request(int userID, int chunkID) {
        if (chunkID < 1 || chunkID > chunks) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : userChunks.entrySet()) {
            if (entry.getValue().contains(chunkID)) {
                res.add(entry.getKey());
            }
        }
        if (!res.isEmpty()) {
            userChunks.computeIfAbsent(userID, k -> new HashSet<>()).add(chunkID);
        }
        return res;
    }
}

/**
 * Your FileSharing object will be instantiated and called as such:
 * FileSharing obj = new FileSharing(m);
 * int param_1 = obj.join(ownedChunks);
 * obj.leave(userID);
 * List<Integer> param_3 = obj.request(userID,chunkID);
 */