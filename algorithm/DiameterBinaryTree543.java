package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 二叉树的直径（LeetCode 543）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：O(h)
 * - h是二叉树的高度，递归调用栈的深度
 * - 最坏情况下（完全不平衡的树）为O(n)，最好情况下（完全平衡的树）为O(log n)
 */
public class DiameterBinaryTree543 {

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

    // 全局变量，用于记录二叉树的最大直径
    private int maxDiameter = 0;

    /**
     * 计算二叉树的直径
     *
     * 算法思路：
     * 二叉树的直径是任意两个节点之间的最长路径长度（边数）
     * 这条路径可能经过根节点，也可能不经过根节点
     * 对于每个节点，经过该节点的最长路径等于其左子树的最大深度加上右子树的最大深度
     * 因此，我们可以在计算每个节点最大深度的同时，更新全局最大直径
     *
     * 执行过程分析（以二叉树 [1,2,3,4,5] 为例）：
     *
     *       1
     *      / \
     *     2   3
     *    / \
     *   4   5
     *
     * 递归调用过程：
     * diameterOfBinaryTree(1)
     * ├─ depth(1)
     * │  ├─ depth(2)
     * │  │  ├─ depth(4) -> 返回1，更新maxDiameter=max(0,0+0)=0
     * │  │  ├─ depth(5) -> 返回1，更新maxDiameter=max(0,0+0)=0
     * │  │  └─ 返回max(1,1)+1=2
     * │  ├─ depth(3)
     * │  │  ├─ depth(null) -> 返回0
     * │  │  ├─ depth(null) -> 返回0
     * │  │  └─ 返回max(0,0)+1=1
     * │  ├─ 更新maxDiameter=max(0,2+1)=3（经过节点2的路径4->2->5最长）
     * │  └─ 返回max(2,1)+1=3
     * └─ 返回maxDiameter=3
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
     * @return 二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // 重置全局变量
        maxDiameter = 0;

        // 计算树的深度，在计算过程中更新最大直径
        depth(root);

        // 返回最大直径
        return maxDiameter;
    }

    /**
     * 辅助方法：计算以当前节点为根的子树的最大深度
     * 同时更新全局最大直径
     *
     * 算法思路：
     * 1. 递归计算左右子树的深度
     * 2. 更新经过当前节点的最长路径（左子树深度+右子树深度）
     * 3. 返回当前子树的最大深度
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param node 当前节点
     * @return 以当前节点为根的子树的最大深度
     */
    private int depth(TreeNode node) {
        // 基础情况：如果节点为空，深度为0
        if (node == null) {
            return 0;
        }

        // 递归计算左子树的最大深度
        int leftDepth = depth(node.left);

        // 递归计算右子树的最大深度
        int rightDepth = depth(node.right);

        // 更新最大直径：经过当前节点的路径长度等于左子树深度加右子树深度
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // 返回以当前节点为根的子树的最大深度
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 另一种实现方式：使用自定义类封装返回值
     *
     * 算法思路：
     * 对于每个节点，我们需要知道两个信息：
     * 1. 以该节点为根的子树的最大深度
     * 2. 以该节点为根的子树中的最大直径
     * 可以用一个类来封装这两个信息
     */
    static class Result {
        int depth;     // 子树的最大深度
        int diameter;  // 子树中的最大直径

        Result(int depth, int diameter) {
            this.depth = depth;
            this.diameter = diameter;
        }
    }

    /**
     * 使用自定义类的解法
     *
     * 算法思路：
     * 通过Result类同时返回子树的深度和最大直径
     * 避免使用全局变量
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     * - Result对象创建：O(n)
     *
     * @param root 二叉树的根节点
     * @return 二叉树的直径
     */
    public int diameterOfBinaryTreeAlternative(TreeNode root) {
        return diameterHelper(root).diameter;
    }

    /**
     * 辅助方法：返回以当前节点为根的子树的深度和最大直径
     *
     * 算法思路：
     * 1. 递归获取左右子树的深度和直径信息
     * 2. 计算当前子树的深度
     * 3. 计算经过当前节点的路径长度
     * 4. 计算当前子树的最大直径
     * 5. 返回封装了深度和直径的Result对象
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param node 当前节点
     * @return 包含深度和直径的Result对象
     */
    private Result diameterHelper(TreeNode node) {
        // 基础情况：如果节点为空
        if (node == null) {
            return new Result(0, 0);
        }

        // 递归获取左右子树的信息
        Result leftResult = diameterHelper(node.left);
        Result rightResult = diameterHelper(node.right);

        // 计算当前子树的最大深度
        int currentDepth = Math.max(leftResult.depth, rightResult.depth) + 1;

        // 计算经过当前节点的路径长度
        int diameterThroughCurrent = leftResult.depth + rightResult.depth;

        // 计算当前子树中的最大直径
        int currentDiameter = Math.max(diameterThroughCurrent,
                                     Math.max(leftResult.diameter, rightResult.diameter));

        return new Result(currentDepth, currentDiameter);
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
     * 主函数：处理用户输入并计算二叉树的直径
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 打印构建的二叉树
     * 4. 调用两种方法计算直径
     * 5. 打印计算结果
     */
    public static void main(String[] args) {
        // 打印程序标题
        System.out.println("二叉树直径计算");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息并退出
            System.out.println("创建的二叉树为空，直径为: 0");
            return;
        }

        // 打印创建的二叉树
        System.out.println("创建的二叉树:");
        printLevelOrder(root);

        // 计算直径
        // 创建解决方案实例
        DiameterBinaryTree543 solution = new DiameterBinaryTree543();
        int diameter1 = solution.diameterOfBinaryTree(root);
        int diameter2 = solution.diameterOfBinaryTreeAlternative(root);

        // 打印结果
        System.out.println("方法1计算的直径: " + diameter1);
        System.out.println("方法2计算的直径: " + diameter2);
    }
}
