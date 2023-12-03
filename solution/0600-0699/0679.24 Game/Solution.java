class Solution {
    private final char[] ops = {'+', '-', '*', '/'};

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int num : cards) {
            nums.add((double) num);
        }
        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        int n = nums.size();
        if (n == 1) {
            return Math.abs(nums.get(0) - 24) < 1e-6;
        }
        boolean ok = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    List<Double> nxt = new ArrayList<>();
                    for (int k = 0; k < n; ++k) {
                        if (k != i && k != j) {
                            nxt.add(nums.get(k));
                        }
                    }
                    for (char op : ops) {
                        switch (op) {
                            case '/' -> {
                                if (nums.get(j) == 0) {
                                    continue;
                                }
                                nxt.add(nums.get(i) / nums.get(j));
                            }
                            case '*' -> {
                                nxt.add(nums.get(i) * nums.get(j));
                            }
                            case '+' -> {
                                nxt.add(nums.get(i) + nums.get(j));
                            }
                            case '-' -> {
                                nxt.add(nums.get(i) - nums.get(j));
                            }
                        }
                        ok |= dfs(nxt);
                        if (ok) {
                            return true;
                        }
                        nxt.remove(nxt.size() - 1);
                    }
                }
            }
        }
        return ok;
    }
}