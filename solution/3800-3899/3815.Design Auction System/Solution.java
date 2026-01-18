class AuctionSystem {
    private final Map<Integer, TreeSet<int[]>> items = new HashMap<>();
    private final Map<Integer, Map<Integer, Integer>> users = new HashMap<>();

    public AuctionSystem() {
    }

    public void addBid(int userId, int itemId, int bidAmount) {
        users.computeIfAbsent(userId, k -> new HashMap<>());

        if (users.get(userId).containsKey(itemId)) {
            removeBid(userId, itemId);
        }

        users.get(userId).put(itemId, bidAmount);

        items.computeIfAbsent(itemId,
            k
            -> new TreeSet<>(
                (a, b)
                    -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])));

        items.get(itemId).add(new int[] {bidAmount, userId});
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        int oldAmount = users.get(userId).get(itemId);
        TreeSet<int[]> set = items.get(itemId);

        set.remove(new int[] {oldAmount, userId});
        set.add(new int[] {newAmount, userId});

        users.get(userId).put(itemId, newAmount);
    }

    public void removeBid(int userId, int itemId) {
        int oldAmount = users.get(userId).get(itemId);
        TreeSet<int[]> set = items.get(itemId);

        set.remove(new int[] {oldAmount, userId});
        users.get(userId).remove(itemId);
    }

    public int getHighestBidder(int itemId) {
        TreeSet<int[]> set = items.get(itemId);
        if (set == null || set.isEmpty()) {
            return -1;
        }
        return set.last()[1];
    }
}

/**
 * Your AuctionSystem object will be instantiated and called as such:
 * AuctionSystem obj = new AuctionSystem();
 * obj.addBid(userId,itemId,bidAmount);
 * obj.updateBid(userId,itemId,newAmount);
 * obj.removeBid(userId,itemId);
 * int param_4 = obj.getHighestBidder(itemId);
 */
