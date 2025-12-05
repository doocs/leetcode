package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 二叉搜索树中第K小的元素（LeetCode 230）
 *
 * 时间复杂度：O(H + k)
 * - H是树的高度
 * - 最坏情况下需要遍历到第k个节点
 *
 * 空间复杂度：O(H)
 * - 递归调用栈的深度为树的高度H
 */
public class KSmall230 {
    private int count = 0; // 计数器，用于记录当前访问的节点数
    private int result = -1; // 存储第 k 小元素的值

    static class TreeNode {
        int val; // 节点值
        TreeNode left; // 左子节点
        TreeNode right; // 右子节点

        TreeNode(int x) {
            val = x; // 构造函数
        }
    }

    /**
     * 中序遍历，查找第 k 小的元素
     *
     * 算法思路：
     * 利用二叉搜索树的性质：中序遍历的结果是有序的
     * 通过中序遍历，当访问到第k个节点时，该节点就是第k小的元素
     *
     * 执行过程分析（以二叉搜索树 [3,1,4,null,2]，k=1 为例）：
     *
     *     3
     *    / \
     *   1   4
     *    \
     *     2
     *
     * 中序遍历顺序：1 -> 2 -> 3 -> 4
     * 第1小的元素是1
     *
     * 时间复杂度分析：
     * - 最多访问k个节点：O(H + k)，其中H为树的高度
     * - 当k较小时，只需要遍历到第k个节点即可停止
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(H)，其中H为树的高度
     *
     * @param node 当前节点
     * @param k 目标位置
     */
    public void inOrderTraversal(TreeNode node, int k) {
        // 如果节点为空或已找到第 k 小元素，返回
        if (node == null || count >= k) return;

        // 遍历左子树
        inOrderTraversal(node.left, k);

        // 访问当前节点
        count++;

        // 如果当前计数等于 k
        if (count == k) {
            // 更新结果
            result = node.val;
            // 找到第 k 小元素，返回
            return;
        }

        // 遍历右子树
        inOrderTraversal(node.right, k);
    }

    /**
     * 查找第 k 小的元素
     *
     * 算法思路：
     * 调用中序遍历方法，在遍历过程中找到第k小的元素
     *
     * 时间复杂度分析：
     * - 与 [inOrderTraversal](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/KSmall230.java#L51-L77) 方法相同：O(H + k)
     *
     * 空间复杂度分析：
     * - 与 [inOrderTraversal](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/KSmall230.java#L51-L77) 方法相同：O(H)
     *
     * @param root 二叉搜索树的根节点
     * @param k 目标位置
     * @return 第k小的元素值
     */
    public int kthSmallest(TreeNode root, int k) {
        // 重置计数器和结果
        count = 0;
        result = -1;

        // 执行中序遍历
        inOrderTraversal(root, k);

        // 返回第 k 小元素
        return result;
    }

    /**
     * 从用户输入构建二叉树
     *
     * 算法思路：
     * 按层序遍历的方式构建二叉树
     * 使用数组存储节点，根据完全二叉树的性质确定父子关系
     *
     * 时间复杂度分析：
     * - 遍历所有输入节点一次：O(n)，其中n为输入节点数
     *
     * 空间复杂度分析：
     * - 存储所有节点：O(n)
     *
     * @param scanner Scanner对象用于读取输入
     * @return 构建完成的二叉树根节点
     */
    public static TreeNode buildTree(Scanner scanner) {
        // 提示用户输入
        System.out.print("请输入节点值（用空格分隔，-1 表示 null）：");
        // 读取输入并分割
        String[] inputs = scanner.nextLine().split(" ");

        // 如果根节点为空
        if (inputs.length == 0 || inputs[0].equals("-1")) return null;

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(inputs[0]));
        // 存储所有节点
        TreeNode[] nodes = new TreeNode[inputs.length];
        // 将根节点存入数组
        nodes[0] = root;

        for (int i = 1; i < inputs.length; i++) {
            // 如果是空节点，跳过
            if (inputs[i].equals("-1")) continue;

            // 创建新节点
            TreeNode currentNode = new TreeNode(Integer.parseInt(inputs[i]));
            // 存入数组
            nodes[i] = currentNode;

            // 计算父节点的索引
            int parentIndex = (i - 1) / 2;

            // 奇数索引为左子节点
            if (i % 2 == 1) {
                nodes[parentIndex].left = currentNode;
            } else {
                nodes[parentIndex].right = currentNode;
            }
        }

        // 返回构建完成的树的根节点
        return root;
    }

    /**
     * 主函数：处理用户输入并查找第K小的元素
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉搜索树节点值
     * 2. 根据输入构建二叉搜索树
     * 3. 提示用户输入k值
     * 4. 调用方法查找第k小的元素
     * 5. 输出结果
     */
    public static void main(String[] args) {
        // 创建扫描器用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 输入二叉树
        TreeNode root = buildTree(scanner);

        // 输入 k 值
        System.out.print("请输入 k 的值：");
        // 读取 k 的值
        int k = scanner.nextInt();

        // 查找第 k 小元素
        KSmall230 solution = new KSmall230();
        int kthSmallest = solution.kthSmallest(root, k);

        // 输出结果
        System.out.println("第 " + k + " 小的元素是：" + kthSmallest);
    }
}
