class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        events.sort((a, b) -> {
            int x = Integer.parseInt(a.get(1));
            int y = Integer.parseInt(b.get(1));
            if (x == y) {
                return a.get(0).charAt(2) - b.get(0).charAt(2);
            }
            return x - y;
        });
        int[] ans = new int[numberOfUsers];
        int[] onlineT = new int[numberOfUsers];
        int lazy = 0;
        for (var e : events) {
            String etype = e.get(0);
            int cur = Integer.parseInt(e.get(1));
            String s = e.get(2);
            if (etype.charAt(0) == 'O') {
                onlineT[Integer.parseInt(s)] = cur + 60;
            } else if (s.charAt(0) == 'A') {
                ++lazy;
            } else if (s.charAt(0) == 'H') {
                for (int i = 0; i < numberOfUsers; ++i) {
                    if (onlineT[i] <= cur) {
                        ++ans[i];
                    }
                }
            } else {
                for (var a : s.split(" ")) {
                    ++ans[Integer.parseInt(a.substring(2))];
                }
            }
        }
        if (lazy > 0) {
            for (int i = 0; i < numberOfUsers; ++i) {
                ans[i] += lazy;
            }
        }
        return ans;
    }
}
