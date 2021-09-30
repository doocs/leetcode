class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for (int item : hand) {
            mp.put(item, mp.getOrDefault(item, 0) + 1);
        }

        while (mp.size() > 0) {
            int start = mp.firstKey();
            for (int i = start; i < start + groupSize; i++) {
                if (!mp.containsKey(i)) {
                    return false;
                }
                int time = mp.get(i);
                if (time == 1) {
                    mp.remove(i);
                } else {
                    mp.replace(i, time - 1);
                }
            }
        }
        return true;
    }
}