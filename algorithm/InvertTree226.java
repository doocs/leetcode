package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 翻转二叉树（LeetCode 226）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：
 * - 方法1（递归）：O(h)
 *   h是二叉树的高度，递归调用栈的深度
 *   最坏情况下（完全不平衡的树）为O(n)，最好情况下（完全平衡的树）为O(log n)
 * - 方法2（迭代）：O(w)
 *   w是二叉树的最大宽度，队列最多存储一层的所有节点
 */
public class InvertTree226 {

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
     * 方法1：递归解法
     *
     * 算法思路：
     * 递归地翻转左右子树，然后交换当前节点的左右子树
     *
     * 执行过程分析（以二叉树 [4,2,7,1,3,6,9] 为例）：
     *
     * 翻转前：
     *       4
     *      / \
     *     2   7
     *    / \ / \
     *   1  3 6  9
     *
     * 翻转后：
     *       4
     *      / \
     *     7   2
     *    / \ / \
     *   9  6 3  1
     *
     * 递归调用过程：
     * invertTree(4)
     * ├─ invertTree(2)
     * │  ├─ invertTree(1) -> 返回1
     * │  ├─ invertTree(3) -> 返回3
     * │  ├─ 交换2的左右子树：2.left=3, 2.right=1
     * │  └─ 返回2
     * ├─ invertTree(7)
     * │  ├─ invertTree(6) -> 返回6
     * │  ├─ invertTree(9) -> 返回9
     * │  ├─ 交换7的左右子树：7.left=9, 7.right=6
     * │  └─ 返回7
     * ├─ 交换4的左右子树：4.left=7, 4.right=2
     * └─ 返回4
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
     * @return 翻转后的二叉树根节点
     */
    public TreeNode invertTree(TreeNode root) {
        // 基础情况：如果节点为空，直接返回 null
        // 这是递归的终止条件
        if (root == null) {
            return null;
        }

        // 递归翻转左子树
        TreeNode left = invertTree(root.left);

        // 递归翻转右子树
        TreeNode right = invertTree(root.right);

        // 交换当前节点的左右子树
        root.right = left;
        root.left = right;

        // 返回当前翻转后的根节点
        return root;
    }

    /**
     * 方法2：迭代解法（层序遍历）
     *
     * 算法思路：
     * 使用队列进行层序遍历，对每个节点交换其左右子树
     *
     * 执行过程分析（以二叉树 [4,2,7,1,3,6,9] 为例）：
     *
     * 翻转前：
     *       4
     *      / \
     *     2   7
     *    / \ / \
     *   1  3 6  9
     *
     * 翻转过程：
     * 1. 处理节点4：交换左右子树(2和7) -> 4的左子树为7，右子树为2
     * 2. 处理节点7：交换左右子树(6和9) -> 7的左子树为9，右子树为6
     * 3. 处理节点2：交换左右子树(1和3) -> 2的左子树为3，右子树为1
     * 4. 处理节点9、6、3、1：它们都是叶子节点，无需交换
     *
     * 翻转后：
     *       4
     *      / \
     *     7   2
     *    / \ / \
     *   9  6 3  1
     *
     * 时间复杂度分析：
     * - 每个节点入队和出队一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 队列最多存储一层的节点数：O(w)，其中w为树的最大宽度
     * - 最坏情况：O(n)，最好情况：O(1)
     *
     * @param root 二叉树的根节点
     * @return 翻转后的二叉树根节点
     */
    public TreeNode invertTreeIterative(TreeNode root) {
        // 如果根节点为空，直接返回null
        if (root == null) {
            return null;
        }

        // 创建队列用于层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);

        // 当队列不为空时继续处理
        while (!queue.isEmpty()) {
            // 从队列中取出一个节点
            TreeNode node = queue.poll();

            // 交换当前节点的左右子树
            // 使用临时变量保存左子树
            TreeNode temp = node.left;
            // 将右子树赋值给左子树
            node.left = node.right;
            // 将临时变量(原左子树)赋值给右子树
            node.right = temp;

            // 将非空的左子节点加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 将非空的右子节点加入队列
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // 返回翻转后的根节点
        return root;
    }

    /**
     * 辅助方法：根据数组创建二叉树（用于测试）
     *
     * 算法思路：
     * 按层序遍历的方式构建二叉树
     * 使用队列来维护当前需要处理子节点的节点
     *
     * 示例：输入"4 2 7 1 3 6 9"
     * 1. 创建根节点4
     * 2. 处理节点4的子节点：左子节点为2，右子节点为7
     * 3. 处理节点2的子节点：左子节点为1，右子节点为3
     * 4. 处理节点7的子节点：左子节点为6，右子节点为9
     *
     * 构建完成的二叉树：
     *       4
     *      / \
     *     2   7
     *    / \ / \
     *   1  3 6  9
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
     *
     * 示例：对于二叉树
     *       4
     *      / \
     *     2   7
     *    / \ / \
     *   1  3 6  9
     *
     * 输出结果：4 2 7 1 3 6 9
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
            // 打印当前节点的值
            System.out.print(node.val + " ");

            // 如果左子节点不为空，将其加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 如果右子节点不为空，将其加入队列
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        // 换行
        System.out.println();
    }

    /**
     * 主函数：处理用户输入并演示翻转二叉树
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 打印翻转前的二叉树
     * 4. 调用翻转方法翻转二叉树
     * 5. 打印翻转后的二叉树
     */
    public static void main(String[] args) {
        // 打印程序标题
        System.out.println("二叉树翻转演示");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息并退出程序
            System.out.println("创建的二叉树为空");
            return;
        }

        // 打印翻转前的二叉树
        System.out.println("翻转前的二叉树:");
        printLevelOrder(root);

        // 翻转二叉树
        // 创建解决方案实例
        InvertTree226 solution = new InvertTree226();
        // 调用invertTree方法翻转二叉树
        TreeNode invertedRoot = solution.invertTree(root);

        // 打印翻转后的二叉树
        System.out.println("翻转后的二叉树:");
        printLevelOrder(invertedRoot);
    }
}
