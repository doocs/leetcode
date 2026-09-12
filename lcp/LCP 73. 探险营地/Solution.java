class Solution {
    public int adventureCamp(String[] expeditions) {
        java.util.Set<String> known = new java.util.HashSet<>();
        for (String camp : expeditions[0].split("->")) {
            if (!camp.isEmpty()) {
                known.add(camp);
            }
        }

        int bestIdx = -1;
        int bestCnt = 0;
        for (int i = 1; i < expeditions.length; i++) {
            int cnt = 0;
            for (String camp : expeditions[i].split("->")) {
                if (!camp.isEmpty() && known.add(camp)) {
                    cnt++;
                }
            }
            if (cnt > bestCnt) {
                bestCnt = cnt;
                bestIdx = i;
            }
        }
        return bestIdx;
    }
}