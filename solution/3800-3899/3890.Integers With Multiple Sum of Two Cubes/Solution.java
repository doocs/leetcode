class Solution {
    private static final int LIMIT = (int) 1e9;
    private static final List<Integer> GOOD = new ArrayList<>();

    static {
        Map<Integer, Integer> cnt = new HashMap<>();
        int[] cubes = new int[1001];
        for (int i = 0; i <= 1000; i++) {
            cubes[i] = i * i * i;
        }
        for (int a = 1; a <= 1000; a++) {
            for (int b = a; b <= 1000; b++) {
                int x = cubes[a] + cubes[b];
                if (x > LIMIT) {
                    break;
                }
                cnt.merge(x, 1, Integer::sum);
            }
        }
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            if (e.getValue() > 1) {
                GOOD.add(e.getKey());
            }
        }

        Collections.sort(GOOD);
    }

    public List<Integer> findGoodIntegers(int n) {
        int idx = Collections.binarySearch(GOOD, n + 1);
        if (idx < 0) {
            idx = -idx - 1;
        }
        return GOOD.subList(0, idx);
    }
}
