class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] pair : pairs) {
            calcBetter(preferences[pair[0]], map, pair[0], pair[1]);
            calcBetter(preferences[pair[1]], map, pair[1], pair[0]);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pairs.length; i++) {
            for (int j = i + 1; j < pairs.length; j++) {
                if (unhappy(pairs[i][0], pairs[j][0], map)) {
                    set.add(pairs[i][0]);
                    set.add(pairs[j][0]);
                }
                if (unhappy(pairs[i][1], pairs[j][0], map)) {
                    set.add(pairs[i][1]);
                    set.add(pairs[j][0]);
                }
                if (unhappy(pairs[i][0], pairs[j][1], map)) {
                    set.add(pairs[i][0]);
                    set.add(pairs[j][1]);
                }
                if (unhappy(pairs[i][1], pairs[j][1], map)) {
                    set.add(pairs[i][1]);
                    set.add(pairs[j][1]);
                }
            }
        }
        return set.size();
    }

    private void calcBetter(int[] preference, Map<Integer, Set<Integer>> map, int from, int to) {
        Set<Integer> betterSet = new HashSet<>();
        for (int i : preference) {
            if (i == to) {
                break;
            }
            betterSet.add(i);
        }
        map.put(from, betterSet);
    }

    private boolean unhappy(int x, int y, Map<Integer, Set<Integer>> map) {
        return map.get(x).contains(y) && map.get(y).contains(x);
    }
}