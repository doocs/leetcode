class Solution {
    public int maximumLength(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int max = 0;
       
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            System.out.println(i.getValue());
            if (i.getValue() >= 2 && i.getKey() != 1) {
                int x = i.getKey();
                int c = 2;
                while (map.containsKey(x * x)) {
                    if (map.get(x * x) == 1) {
                        max = Math.max(max, c + 1);
                        break;
                    } else if (map.get(x * x) >= 2) {
                        max = Math.max(max, c + 1);
                        x = x * x;
                    }
                    c += 2;
                }
            }
        }
        if (map.containsKey(1) && map.get(1) - 1 > max) {
            return (map.get(1) % 2 != 0) ? map.get(1) : map.get(1) - 1;
        }
        return max == 0 ? 1 : max;
    }
}
