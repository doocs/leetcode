class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : hand) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        Arrays.sort(hand);
        for (int v : hand) {
            if (cnt.containsKey(v)) {
                for (int x = v; x < v + groupSize; ++x) {
                    if (!cnt.containsKey(x)) {
                        return false;
                    }
                    cnt.put(x, cnt.get(x) - 1);
                    if (cnt.get(x) == 0) {
                        cnt.remove(x);
                    }
                }
            }
        }
        return true;
    }
}