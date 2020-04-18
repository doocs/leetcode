class Solution {
    /**
     * 贪心算法
     */
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        // 首先检查是否存在所有项目都可投资且初始资本 W >= max(Capital) 的情况。如果是，返回利润中前 k 个最大元素的和。
        boolean speedUp = true;
        for (int c : Capital) if (W < c) speedUp = false;
        if (speedUp) {
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int p : Profits) {
                heap.add(p);
                if (heap.size() > k) heap.poll();
            }
            for (int h : heap) W += h;
            return W;
        }

        int idx;
        int n = Profits.length;
        for (int i = 0; i < Math.min(k, n); ++i) {
            idx = -1;
            // 找到获取利润最多的项目
            for (int j = 0; j < n; ++j) {
                if (W >= Capital[j]) {
                    if (idx == -1) idx = j;
                    else if (Profits[idx] < Profits[j]) idx = j;
                }
            }
            // 找不到合适的项目
            if (idx == -1) break;
            // 累计当前项目的利润到总利润中，并把当前项目标记为"不可用"（设置成最大整数）
            W += Profits[idx];
            Capital[idx] = Integer.MAX_VALUE;
        }
        return W;
    }
} 
