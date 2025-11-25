package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 二叉树的右视图（LeetCode 199）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：O(w)
 * - w是二叉树的最大宽度
 * - 队列最多存储一层的所有节点
 */
public class RightSideView199 {

    /**
     * 二叉树节点定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 获取二叉树的右视图
     *
     * 算法思路：
     * 使用层序遍历（BFS），每层只记录最右边的节点值
     * 在每层遍历时，将最后一个访问的节点值加入结果列表
     *
     * 执行过程分析（以二叉树 [1,2,3,null,5,null,4] 为例）：
     *
     *     1            <- 右视图节点: 1
     *   /   \
     *  2     3         <- 右视图节点: 3
     *   \     \
     *    5     4       <- 右视图节点: 4
     *
     * 层序遍历过程：
     * 第1层: [1] -> 记录1
     * 第2层: [2,3] -> 记录3
     * 第3层: [5,4] -> 记录4
     *
     * 右视图结果: [1,3,4]
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 队列最多存储一层的节点数：O(w)，其中w为树的最大宽度
     * - 结果列表存储右视图节点：O(h)，其中h为树的高度
     * - 总空间复杂度：O(w)
     *
     * @param root 二叉树的根节点
     * @return 从右侧看到的节点值列表
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 创建结果列表，用于存储右视图节点值
        List<Integer> result = new ArrayList<>();

        // 如果根节点为空，直接返回空结果
        if (root == null) {
            return result;
        }

        // 使用队列进行广度优先搜索
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 当队列不为空时继续遍历
        while (!queue.isEmpty()) {
            // 记录当前层的节点数
            int size = queue.size();

            // 处理当前层的所有节点
            for (int i = 0; i < size; i++) {
                // 弹出队列中的节点
                TreeNode node = queue.poll();

                // 如果是当前层的最后一个节点，将其值加入结果列表
                if (i == size - 1) {
                    result.add(node.val);
                }

                // 将下一层的节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return result;
    }

    /**
     * 递归解法
     *
     * 算法思路：
     * 使用深度优先搜索（DFS），优先遍历右子树
     * 对于每个深度，只记录第一次访问到的节点值（即该层最右边的节点）
     *
     * 执行过程分析（以二叉树 [1,2,3,null,5,null,4] 为例）：
     *
     *     1            <- 深度0: 记录1
     *   /   \
     *  2     3         <- 深度1: 记录3(右子树优先)
     *   \     \
     *    5     4       <- 深度2: 记录4
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     * - 结果列表存储右视图节点：O(h)
     * - 总空间复杂度：O(h)
     *
     * @param root 二叉树的根节点
     * @return 从右侧看到的节点值列表
     */
    public List<Integer> rightSideViewRecursive(TreeNode root) {
        // 创建结果列表，用于存储右视图节点值
        List<Integer> result = new ArrayList<>();
        rightSideViewHelper(root, 0, result);
        return result;
    }

    /**
     * 递归辅助方法
     *
     * 算法思路：
     * 1. 如果节点为空，直接返回
     * 2. 如果当前深度等于结果列表大小，说明是该层第一次访问，记录节点值
     * 3. 优先递归处理右子树，再处理左子树
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param node 当前节点
     * @param depth 当前深度
     * @param result 结果列表
     */
    private void rightSideViewHelper(TreeNode node, int depth, List<Integer> result) {
        // 基础情况：如果节点为空，直接返回
        if (node == null) {
            return;
        }

        // 如果当前深度等于结果列表大小，说明是该层第一次访问
        if (result.size() == depth) {
            result.add(node.val);
        }

        // 优先递归处理右子树，再处理左子树
        rightSideViewHelper(node.right, depth + 1, result);
        rightSideViewHelper(node.left, depth + 1, result);
    }

    /**
     * 辅助方法：根据数组创建二叉树（用于测试）
     *
     * 算法思路：
     * 按层序遍历的方式构建二叉树
     * 使用队列来维护当前需要处理子节点的节点
     */
    public static TreeNode createTree() {
        // 创建Scanner对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入二叉树节点值
        System.out.println("请输入二叉树节点值，按层序遍历输入，null表示空节点，用空格分隔：");
        // 读取用户输入的一行
        String input = scanner.nextLine();
        // 按空格分割输入字符串得到节点值数组
        String[] values = input.split(" ");

        // 检查输入是否为空
        if (values.length == 0 || "null".equals(values[0]) || values[0].isEmpty()) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        // 创建队列用于构建二叉树
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);

        // 数组索引，从1开始处理子节点
        int i = 1;
        // 当队列不为空且索引未越界时继续处理
        while (!queue.isEmpty() && i < values.length) {
            // 从队列中取出一个节点
            TreeNode node = queue.poll();

            // 处理左子节点
            // 检查左子节点是否存在且不为null
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                // 创建左子节点
                node.left = new TreeNode(Integer.parseInt(values[i]));
                // 将左子节点加入队列
                queue.offer(node.left);
            }
            // 索引递增
            i++;

            // 处理右子节点
            // 检查右子节点是否存在且不为null
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                // 创建右子节点
                node.right = new TreeNode(Integer.parseInt(values[i]));
                // 将右子节点加入队列
                queue.offer(node.right);
            }
            // 索引递增
            i++;
        }

        // 返回构建完成的二叉树根节点
        return root;
    }

    /**
     * 辅助方法：层序遍历打印二叉树
     *
     * 算法思路：
     * 使用队列进行层序遍历，依次打印每个节点的值
     */
    public static void printLevelOrder(TreeNode root) {
        // 检查根节点是否为空
        if (root == null) {
            // 如果为空，打印提示信息并返回
            System.out.println("空树");
            return;
        }

        // 创建队列用于层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);
        // 打印提示信息
        System.out.print("层序遍历结果: ");

        // 当队列不为空时继续遍历
        while (!queue.isEmpty()) {
            // 从队列中取出一个节点
            TreeNode node = queue.poll();
            if (node == null) {
                System.out.print("null ");
            } else {
                System.out.print(node.val + " ");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    /**
     * 主函数：处理用户输入并获取二叉树的右视图
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 打印构建的二叉树
     * 4. 调用两种方法获取右视图
     * 5. 打印右视图结果
     */
    public static void main(String[] args) {
        System.out.println("二叉树右视图");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息并退出
            System.out.println("创建的二叉树为空");
            return;
        }

        // 打印创建的二叉树
        System.out.println("创建的二叉树:");
        printLevelOrder(root);

        // 获取右视图
        // 创建解决方案实例
        RightSideView199 solution = new RightSideView199();
        List<Integer> result1 = solution.rightSideView(root);
        List<Integer> result2 = solution.rightSideViewRecursive(root);

        // 打印结果
        System.out.println("迭代方法右视图结果: " + result1);
        System.out.println("递归方法右视图结果: " + result2);
    }
}
