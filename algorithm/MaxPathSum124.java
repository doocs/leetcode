package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 二叉树中的最大路径和（LeetCode 124）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：O(h)
 * - h是二叉树的高度
 * - 递归调用栈的深度
 */
public class MaxPathSum124 {

    // 定义二叉树节点类
    static class TreeNode {
        int val; // 节点值
        TreeNode left; // 左子节点
        TreeNode right; // 右子节点
        TreeNode(int x) { val = x; } // 构造函数
    }

    // 记录最大路径和
    private int maxPathSum = Integer.MIN_VALUE;

    /**
     * 主方法，计算二叉树的最大路径和
     *
     * 算法思路：
     * 对于每个节点，我们考虑经过该节点的最大路径和
     * 路径可以是：
     * 1. 只包含当前节点
     * 2. 当前节点 + 左子树路径
     * 3. 当前节点 + 右子树路径
     * 4. 左子树路径 + 当前节点 + 右子树路径
     *
     * 执行过程分析（以二叉树 [1,2,3] 为例）：
     *
     *     1
     *    / \
     *   2   3
     *
     * 计算过程：
     * 1. 节点2：最大贡献值为2
     * 2. 节点3：最大贡献值为3
     * 3. 节点1：最大路径和为2+1+3=6
     *
     * 时间复杂度分析：
     * - 调用 [maxGain](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/MaxPathSum124.java#L101-L124) 方法遍历所有节点：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     *
     * @param root 二叉树的根节点
     * @return 二叉树的最大路径和
     */
    public int maxPathSum(TreeNode root) {
        // 重置最大路径和
        maxPathSum = Integer.MIN_VALUE;
        // 调用辅助方法计算最大贡献值
        maxGain(root);
        // 返回最大路径和
        return maxPathSum;
    }

    /**
     * 辅助方法，计算节点的最大贡献值
     *
     * 算法思路：
     * 1. 递归计算左右子树的最大贡献值
     * 2. 计算经过当前节点的最大路径和
     * 3. 更新全局最大路径和
     * 4. 返回当前节点对父节点的最大贡献值
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param node 当前节点
     * @return 当前节点对父节点的最大贡献值
     */
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左子树和右子树的最大贡献值
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 更新最大路径和
        maxPathSum = Math.max(maxPathSum, node.val + leftGain + rightGain);

        // 返回当前节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    /**
     * 创建二叉树的方法
     *
     * 算法思路：
     * 按层序遍历的方式构建二叉树
     * 使用数组存储节点，根据完全二叉树的性质确定父子关系
     *
     * 时间复杂度分析：
     * - 遍历所有输入节点：O(n)
     *
     * 空间复杂度分析：
     * - 存储节点数组：O(n)
     *
     * @return 构建完成的二叉树根节点
     */
    public static TreeNode createTree() {
        // 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入
        System.out.println("请输入树的节点值，以-1表示空节点（按层次遍历输入）：");
        // 读取输入并分割
        String input = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] values = input.split(" ");

        if (values.length == 0 || values[0].equals("-1")) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        // 存储所有节点
        TreeNode[] nodes = new TreeNode[values.length];
        // 将根节点放入数组中
        nodes[0] = root;

        // 按层次遍历构建树
        for (int i = 1; i < values.length; i++) {
            if (!values[i].equals("-1")) {
                // 创建新节点
                nodes[i] = new TreeNode(Integer.parseInt(values[i]));
                // 判断当前节点是左子节点还是右子节点
                if (i % 2 == 1) {
                    nodes[(i - 1) / 2].left = nodes[i];
                } else {
                    nodes[(i - 2) / 2].right = nodes[i];
                }
            } else {
                nodes[i] = null;
            }
        }

        // 返回构建完成的树的根节点
        return root;
    }

    /**
     * 主函数：处理用户输入并计算二叉树中的最大路径和
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 调用方法计算最大路径和
     * 4. 输出计算结果
     */
    public static void main(String[] args) {
        // 创建Solution对象
        MaxPathSum124 solution = new MaxPathSum124();
        // 创建二叉树
        TreeNode root = createTree();
        // 计算最大路径和
        int result = solution.maxPathSum(root);
        // 输出结果
        System.out.println("二叉树的最大路径和为：" + result);
    }
}
