class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        if (n == 1) {
            return heights[0];
        }

        // 创建一个新的数组，数组长度为 n + 1，最后一个元素值赋为 0
        // 确保在后面的遍历中，原数组最后一个元素值能得到计算
        int[] heightss = new int[n + 1];
        heightss[n] = 0;
        for (int i = 0; i < n; ++i) {
            heightss[i] = heights[i];
        }

        Stack<Integer> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i <= n;) {
            if (stack.isEmpty() || heightss[i] > heightss[stack.peek()]) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                max = Math.max(max, heightss[index] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }

        return max;

    }
}