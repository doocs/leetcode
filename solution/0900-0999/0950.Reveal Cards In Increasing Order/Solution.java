class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Deque<Integer> q = new ArrayDeque<>();
        Arrays.sort(deck);
        int n = deck.length;
        for (int i = n - 1; i >= 0; --i) {
            if (!q.isEmpty()) {
                q.offerFirst(q.pollLast());
            }
            q.offerFirst(deck[i]);
        }
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = q.pollLast();
        }
        return ans;
    }
}