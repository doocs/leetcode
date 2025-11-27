package com.funian.algorithm.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 二叉树的最大深度（LeetCode 104）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：
 * - 方法1（递归DFS）：O(h)
 *   h是二叉树的高度，递归调用栈的深度
 *   最坏情况下（完全不平衡的树）为O(n)，最好情况下（完全平衡的树）为O(log n)
 * - 方法2（迭代BFS）：O(w)
 *   w是二叉树的最大宽度，队列最多存储一层的所有节点
 */
public class MaxDepthBinaryTree104 {

    /**
     * 二叉树节点定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    /**
     * 方法1：递归解法（深度优先搜索）
     *
     * 算法思路：
     * 二叉树的最大深度等于左子树和右子树最大深度的最大值加1
     * 递归计算左右子树的深度，取较大值加1
     *
     * 执行过程分析（以二叉树 [3,9,20,null,null,15,7] 为例）：
     *
     *       3
     *      / \
     *     9  20
     *       /  \
     *      15   7
     *
     * 递归调用过程：
     * maxDepth(3)
     * ├─ maxDepth(9)
     * │  ├─ maxDepth(null) -> 0
     * │  └─ maxDepth(null) -> 0
     * │  └─ return max(0,0)+1 = 1
     * ├─ maxDepth(20)
     * │  ├─ maxDepth(15)
     * │  │  ├─ maxDepth(null) -> 0
     * │  │  └─ maxDepth(null) -> 0
     * │  │  └─ return max(0,0)+1 = 1
     * │  ├─ maxDepth(7)
     * │  │  ├─ maxDepth(null) -> 0
     * │  │  └─ maxDepth(null) -> 0
     * │  │  └─ return max(0,0)+1 = 1
     * │  └─ return max(1,1)+1 = 2
     * └─ return max(1,2)+1 = 3
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     * - 最坏情况（链状树）：O(n)
     * - 最好情况（平衡树）：O(log n)
     *
     * @param root 二叉树的根节点
     * @return 二叉树的最大深度
     */
    public int maxDepthRecursive(TreeNode root) {
        // 基础情况：如果节点为空，深度为0
        if (root == null) {
            return 0;
        }

        // 递归计算左子树的最大深度
        int leftDepth = maxDepthRecursive(root.left);

        // 递归计算右子树的最大深度
        int rightDepth = maxDepthRecursive(root.right);

        // 返回左右子树最大深度的最大值加1
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 方法2：迭代解法（广度优先搜索，层序遍历）
     *
     * 算法思路：
     * 使用队列进行层序遍历，每遍历完一层，深度加1
     *
     * 执行过程分析（以二叉树 [3,9,20,null,null,15,7] 为例）：
     *
     *       3
     *      / \
     *     9  20
     *       /  \
     *      15   7
     *
     * 执行步骤：
     * 第1层：队列=[3]，size=1
     *        弹出3，加入9和20，队列=[9,20]
     *        depth=1
     *
     * 第2层：队列=[9,20]，size=2
     *        弹出9，无子节点，队列=[20]
     *        弹出20，加入15和7，队列=[15,7]
     *        depth=2
     *
     * 第3层：队列=[15,7]，size=2
     *        弹出15，无子节点，队列=[7]
     *        弹出7，无子节点，队列=[]
     *        depth=3
     *
     * 队列为空，返回depth=3
     *
     * 时间复杂度分析：
     * - 每个节点入队和出队一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 队列最多存储一层的节点数：O(w)，其中w为树的最大宽度
     * - 最坏情况：O(n)，最好情况：O(1)
     *
     * @param root 二叉树的根节点
     * @return 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        // 如果根节点为空，树的深度为 0
        if (root == null) {
            return 0;
        }

        // 使用队列进行广度优先搜索（BFS）
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        // 将根节点加入队列
        queue.offer(root);

        // 用于存储树的深度
        int depth = 0;

        // 当队列不为空时，说明还有节点未遍历
        while (!queue.isEmpty()) {
            // 获取当前层的节点数
            int size = queue.size();

            // 遍历当前层的所有节点
            while (size > 0) {
                // 弹出队列中的节点
                TreeNode node = queue.poll();

                // 如果当前节点有左子节点，将左子节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }

                // 如果当前节点有右子节点，将右子节点加入队列
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // 当前层节点数量减1
                size--;
            }

            // 每遍历完一层，深度加1
            depth++;
        }

        // 返回二叉树的最大深度
        return depth;
    }


    /**
     * 辅助方法：根据数组创建二叉树（用于测试）
     * 注意：这里采用层序遍历的输入方式，null表示空节点
     */
    public static TreeNode createTree() {
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入
        System.out.println("请输入二叉树节点值，按层序遍历输入，null表示空节点，用空格分隔：");
        // 读取一行输入
        String input = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] values = input.split(" ");

        if (values.length == 0 || "null".equals(values[0]) || values[0].isEmpty()) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        // 创建队列用于层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // i 数组索引
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();

            // 处理左子节点
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.left);
            }
            i++;

            // 处理右子节点
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.right);
            }
            i++;
        }

        // 返回根节点
        return root;
    }

    /**
     * 主函数：处理用户输入并计算二叉树的最大深度
     */
    public static void main(String[] args) {
        // 打印标题
        System.out.println("二叉树最大深度计算");

        // 创建二叉树
        TreeNode root = createTree();

        if (root == null) {
            System.out.println("创建的二叉树为空，最大深度为: 0");
            return;
        }

        System.out.println("二叉树创建成功");

        // 计算最大深度
        // 创建解决方案实例
        MaxDepthBinaryTree104 solution = new MaxDepthBinaryTree104();
        int depth1 = solution.maxDepthRecursive(root);
        int depth2 = solution.maxDepth(root);

        // 打印结果
        System.out.println("递归方法计算的最大深度: " + depth1);
        System.out.println("迭代方法计算的最大深度: " + depth2);
    }
}
