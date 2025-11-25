package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 对称二叉树（LeetCode 101）
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
public class IsSymmetrix101 {

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
     * 一个二叉树是对称的，当且仅当它的左子树和右子树互为镜像
     * 两个树互为镜像的条件：
     * 1. 它们的根节点值相同
     * 2. 每个树的右子树都与另一个树的左子树镜像对称
     *
     * 执行过程分析（以对称二叉树 [1,2,2,3,4,4,3] 为例）：
     *
     *       1
     *      / \
     *     2   2
     *    / \ / \
     *   3  4 4  3
     *
     * 递归调用过程：
     * isSymmetric(1)
     * └─ isMirror(2, 2)
     *    ├─ 2.val == 2.val -> true
     *    ├─ isMirror(2.left=3, 2.right=3)
     *    │  ├─ 3.val == 3.val -> true
     *    │  ├─ isMirror(3.left=null, 3.right=null) -> true
     *    │  └─ isMirror(3.right=null, 3.left=null) -> true
     *    │  └─ 返回 true
     *    ├─ isMirror(2.right=4, 2.left=4)
     *    │  ├─ 4.val == 4.val -> true
     *    │  ├─ isMirror(4.left=null, 4.right=null) -> true
     *    │  └─ isMirror(4.right=null, 4.left=null) -> true
     *    │  └─ 返回 true
     *    └─ 返回 true
     *
     * 时间复杂度分析：
     * - 每个节点最多访问一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     * - 最坏情况（链状树）：O(n)
     * - 最好情况（平衡树）：O(log n)
     *
     * @param root 二叉树的根节点
     * @return 如果是对称二叉树返回true，否则返回false
     */
    public boolean isSymmetric(TreeNode root) {
        // 空树被认为是对称的
        if (root == null) {
            return true;
        }

        // 判断左子树和右子树是否互为镜像
        return isMirror(root.left, root.right);
    }

    /**
     * 辅助方法：判断两个树是否互为镜像
     *
     * 算法思路：
     * 两个树互为镜像需要满足：
     * 1. 两个节点都为空（基础情况1）
     * 2. 只有一个节点为空（基础情况2，返回false）
     * 3. 节点值相等，且左树的左子树与右树的右子树镜像，左树的右子树与右树的左子树镜像
     *
     * 时间复杂度分析：
     * - 每对节点最多访问一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     *
     * @param t1 第一个树的根节点
     * @param t2 第二个树的根节点
     * @return 如果两个树互为镜像返回true，否则返回false
     */
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // 基础情况1：两个节点都为空，是对称的
        if (t1 == null && t2 == null) {
            return true;
        }

        // 基础情况2：只有一个节点为空，不是对称的
        if (t1 == null || t2 == null) {
            return false;
        }

        // 递归判断：
        // 1. 当前节点值相等
        // 2. t1的左子树与t2的右子树互为镜像
        // 3. t1的右子树与t2的左子树互为镜像
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    /**
     * 方法2：迭代解法
     *
     * 算法思路：
     * 使用队列进行层序遍历，每次从队列中取出两个节点进行比较
     * 这两个节点应该是互为镜像位置的节点
     *
     * 执行过程分析（以对称二叉树 [1,2,2,3,4,4,3] 为例）：
     *
     *       1
     *      / \
     *     2   2
     *    / \ / \
     *   3  4 4  3
     *
     * 执行步骤：
     * 1. 队列=[2,2]（根节点的左右子节点）
     *    取出2和2：值相等，将(3,3)和(4,4)加入队列
     *
     * 2. 队列=[3,3,4,4]
     *    取出3和3：值相等，左右子节点都为空
     *
     * 3. 队列=[4,4]
     *    取出4和4：值相等，左右子节点都为空
     *
     * 4. 队列=[]
     *    返回true
     *
     * 时间复杂度分析：
     * - 每个节点最多入队和出队一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 队列最多存储一层的节点数：O(w)，其中w为树的最大宽度
     * - 最坏情况：O(n)，最好情况：O(1)
     *
     * @param root 二叉树的根节点
     * @return 如果是对称二叉树返回true，否则返回false
     */
    public boolean isSymmetricIterative(TreeNode root) {
        // 空树被认为是对称的
        if (root == null) {
            return true;
        }

        // 创建队列用于迭代比较
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点的左右子节点加入队列
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            // 取出两个节点进行比较
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            // 如果两个节点都为空，继续下一轮比较
            if (t1 == null && t2 == null) {
                continue;
            }

            // 如果只有一个节点为空，或者节点值不相等，不是对称的
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }

            // 将镜像位置的子节点加入队列
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }

        return true;
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
     * 主函数：处理用户输入并判断二叉树是否对称
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 打印构建的二叉树
     * 4. 调用两种方法判断是否对称
     * 5. 打印判断结果
     */
    public static void main(String[] args) {
        // 打印程序标题
        System.out.println("对称二叉树判断");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息
            System.out.println("创建的二叉树为空，空树被认为是对称的");
            // 打印结果
            System.out.println("结果: true");
            // 退出程序
            return;
        }

        // 打印创建的二叉树
        System.out.println("创建的二叉树:");
        printLevelOrder(root);

        // 判断是否对称
        // 创建解决方案实例
        IsSymmetrix101 solution = new IsSymmetrix101();
        boolean result1 = solution.isSymmetric(root);
        boolean result2 = solution.isSymmetricIterative(root);

        // 打印结果
        System.out.println("递归方法判断结果: " + result1);
        System.out.println("迭代方法判断结果: " + result2);
    }
}
