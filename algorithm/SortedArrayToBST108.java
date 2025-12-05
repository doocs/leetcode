package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 将有序数组转换为二叉搜索树（LeetCode 108）
 *
 * 时间复杂度：O(n)
 * - n是数组中的元素个数
 * - 每个元素都需要被访问一次来创建对应的树节点
 *
 * 空间复杂度：O(log n)
 * - 递归调用栈的深度为log n（平衡二叉树的高度）
 * - 如果算上返回的树结构，则空间复杂度为O(n)
 */
public class SortedArrayToBST108 {

    /**
     * 二叉树节点定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 将升序数组转换为高度平衡的二叉搜索树
     *
     * 算法思路：
     * 由于数组已经排序，我们可以采用分治的思想：
     * 1. 选择数组中间的元素作为根节点
     * 2. 递归地将左半部分数组构建成左子树
     * 3. 递归地将右半部分数组构建成右子树
     *
     * 执行过程分析（以数组 [-10,-3,0,5,9] 为例）：
     *
     * 初始数组: [-10, -3, 0, 5, 9]
     *
     * 递归调用过程：
     * buildBST(nums, 0, 4)
     * ├─ mid = 2, nums[2] = 0 -> 创建根节点0
     * ├─ buildBST(nums, 0, 1) // 构建左子树
     * │  ├─ mid = 0, nums[0] = -10 -> 创建节点-10
     * │  ├─ buildBST(nums, 0, -1) -> 返回null
     * │  └─ buildBST(nums, 1, 1)
     * │     ├─ mid = 1, nums[1] = -3 -> 创建节点-3
     * │     ├─ buildBST(nums, 1, 0) -> 返回null
     * │     └─ buildBST(nums, 2, 1) -> 返回null
     * └─ buildBST(nums, 3, 4) // 构建右子树
     *    ├─ mid = 3, nums[3] = 5 -> 创建节点5
     *    ├─ buildBST(nums, 3, 2) -> 返回null
     *    └─ buildBST(nums, 4, 4)
     *       ├─ mid = 4, nums[4] = 9 -> 创建节点9
     *       ├─ buildBST(nums, 4, 3) -> 返回null
     *       └─ buildBST(nums, 5, 4) -> 返回null
     *
     * 构建的二叉搜索树：
     *       0
     *      / \
     *   -10   5
     *     \    \
     *     -3    9
     *
     * 时间复杂度分析：
     * - 每个数组元素访问一次：O(n)，其中n为数组长度
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(log n)
     * - 树节点存储空间：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param nums 有序数组
     * @return 构建的平衡二叉搜索树的根节点
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    /**
     * 递归构建平衡二叉搜索树
     *
     * 算法思路：
     * 1. 选取中间元素作为根节点确保左右子树节点数尽可能平衡
     * 2. 递归处理左右子数组构建左右子树
     * 3. 基础情况：当left > right时返回null
     *
     * 时间复杂度分析：
     * - 每个数组元素处理一次：O(n)
     * - 递归深度：O(log n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(log n)
     *
     * @param nums 有序数组
     * @param left 左边界索引
     * @param right 右边界索引
     * @return 构建的子树根节点
     */
    private TreeNode buildBST(int[] nums, int left, int right) {
        // 基本情况：如果左指针大于右指针，返回 null
        // 这表示当前子数组为空
        if (left > right) {
            return null;
        }

        // 找到中间索引，作为当前子树的根节点
        // 使用 left + (right - left) / 2 避免整数溢出
        int mid = left + (right - left) / 2;

        // 创建当前根节点，值为中间元素
        TreeNode root = new TreeNode(nums[mid]);

        // 递归构建左子树和右子树
        root.left = buildBST(nums, left, mid - 1);    // 左半部分数组构建成左子树
        root.right = buildBST(nums, mid + 1, right);  // 右半部分数组构建成右子树

        return root; // 返回当前根节点
    }

    /**
     * 辅助方法：中序遍历打印树结构
     *
     * 算法思路：
     * 按照左-根-右的顺序遍历二叉树，对于BST来说会输出有序序列
     *
     * 时间复杂度分析：
     * - 访问每个节点一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(h)，其中h为树高度
     *
     * @param root 树的根节点
     */
    private static void printTreeInOrder(TreeNode root) {
        if (root != null) {
            printTreeInOrder(root.left);
            System.out.print(root.val + " ");
            printTreeInOrder(root.right);
        }
    }

    /**
     * 辅助方法：层序遍历打印树结构
     *
     * 算法思路：
     * 使用队列按层级顺序遍历二叉树
     *
     * 时间复杂度分析：
     * - 访问每个节点一次：O(n)
     *
     * 空间复杂度分析：
     * - 队列存储节点：O(w)，其中w为树的最大宽度
     *
     * @param root 树的根节点
     */
    private static void printTreeLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        System.out.print("层序遍历: ");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    /**
     * 辅助方法：读取用户输入的有序数组
     *
     * 算法思路：
     * 从标准输入读取一行，按空格分割并转换为整数数组
     *
     * 时间复杂度分析：
     * - 处理输入字符串：O(m)，其中m为输入字符数
     * - 转换为整数：O(n)，其中n为数组长度
     *
     * 空间复杂度分析：
     * - 存储字符串数组：O(m)
     * - 存储整数数组：O(n)
     *
     * @return 用户输入的有序数组
     */
    private static int[] readSortedArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入有序数组元素，以空格分隔：");
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]);
        }

        return nums;
    }

    /**
     * 主函数：处理用户输入并演示将有序数组转换为二叉搜索树
     */
    public static void main(String[] args) {
        System.out.println("将有序数组转换为高度平衡的二叉搜索树");

        // 读取用户输入的有序数组
        int[] nums = readSortedArray();

        System.out.println("输入的有序数组: " + Arrays.toString(nums));

        // 创建解决方案对象并构建平衡二叉搜索树
        SortedArrayToBST108 solution = new SortedArrayToBST108();
        TreeNode root = solution.sortedArrayToBST(nums);

        // 输出结果
        System.out.println("\n构建的二叉搜索树:");
        System.out.print("中序遍历: ");
        printTreeInOrder(root);
        System.out.println();

        printTreeLevelOrder(root);
    }
}
