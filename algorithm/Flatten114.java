package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 二叉树展开为链表（LeetCode 114）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：O(h)
 * - h是二叉树的高度
 * - 递归调用栈的深度
 */
public class Flatten114 {

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
     * 将二叉树展开为链表
     *
     * 算法思路：
     * 使用后序遍历的思想，对于每个节点：
     * 1. 递归展开左子树
     * 2. 递归展开右子树
     * 3. 将左子树连接到右子树位置
     * 4. 将原来的右子树连接到当前链表的末尾
     *
     * 执行过程分析（以二叉树 [1,2,5,3,4,null,6] 为例）：
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     *
     * 展开过程：
     * 1. 展开节点3：3
     * 2. 展开节点4：4
     * 3. 展开节点2：
     *    - 左子树2->3->4
     *    - 右子树为空
     *    - 结果：2->3->4
     * 4. 展开节点6：6
     * 5. 展开节点5：
     *    - 左子树为空
     *    - 右子树5->6
     *    - 结果：5->6
     * 6. 展开节点1：
     *    - 左子树1->2->3->4
     *    - 右子树1->2->3->4->5->6
     *    - 结果：1->2->3->4->5->6
     *
     * 最终链表：1->2->3->4->5->6（右子节点连接，左子节点为null）
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     *
     * @param root 二叉树的根节点
     */
    public void flatten(TreeNode root) {
        // 调用递归辅助方法
        flattenHelper(root);
    }

    /**
     * 递归辅助方法
     *
     * 算法思路：
     * 1. 递归处理左子树和右子树
     * 2. 将左子树移动到右子树位置
     * 3. 将原来的右子树连接到新链表末尾
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param root 当前节点
     * @return 展开后链表的尾节点
     */
    private TreeNode flattenHelper(TreeNode root) {
        // 基础情况：如果节点为空，返回null
        if (root == null) {
            return null;
        }

        // 递归处理左子树和右子树
        TreeNode leftTail = flattenHelper(root.left);
        TreeNode rightTail = flattenHelper(root.right);

        // 如果左子树不为空
        if (leftTail != null) {
            // 将左子树的尾部连接到原来的右子树
            leftTail.right = root.right;

            // 将左子树移动到右子树位置
            root.right = root.left;

            // 将左子树置为空
            root.left = null;
        }

        // 确定并返回链表的尾节点
        if (rightTail != null) {
            return rightTail;
        } else if (leftTail != null) {
            return leftTail;
        } else {
            return root;
        }
    }

    /**
     * 迭代解法
     *
     * 算法思路：
     * 使用栈进行前序遍历，在遍历过程中重新连接节点
     *
     * 时间复杂度分析：
     * - 每个节点入栈和出栈一次：O(n)
     *
     * 空间复杂度分析：
     * - 显式栈最多存储树的高度个节点：O(h)
     *
     * @param root 二叉树的根节点
     */
    public void flattenIterative(TreeNode root) {
        // 如果根节点为空，直接返回
        if (root == null) {
            return;
        }

        // 创建栈用于迭代遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // 当栈不为空时继续遍历
        while (!stack.isEmpty()) {
            // 弹出栈顶节点
            TreeNode current = stack.pop();

            // 先将右子节点入栈（如果存在）
            if (current.right != null) {
                stack.push(current.right);
            }

            // 再将左子节点入栈（如果存在）
            if (current.left != null) {
                stack.push(current.left);
            }

            // 如果栈不为空，将当前节点的右指针指向栈顶节点
            if (!stack.isEmpty()) {
                current.right = stack.peek();
            }

            // 将当前节点的左指针置为空
            current.left = null;
        }
    }

    /**
     * 原地解法（O(1)空间复杂度）
     *
     * 算法思路：
     * 对于每个节点，如果存在左子树：
     * 1. 找到左子树的最右节点
     * 2. 将原右子树连接到该节点的右子树
     * 3. 将左子树移动到右子树位置
     * 4. 将左子树置为空
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     * - 寻找前驱节点的总时间复杂度也是O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param root 二叉树的根节点
     */
    public void flattenInPlace(TreeNode root) {
        TreeNode current = root;

        // 当当前节点不为空时继续
        while (current != null) {
            // 如果当前节点存在左子树
            if (current.left != null) {
                // 找到左子树的最右节点
                TreeNode predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // 将原右子树连接到前驱节点的右子树
                predecessor.right = current.right;

                // 将左子树移动到右子树位置
                current.right = current.left;

                // 将左子树置为空
                current.left = null;
            }

            // 移动到下一个节点
            current = current.right;
        }
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
     * 辅助方法：打印展开后的链表
     *
     * 算法思路：
     * 按照右子节点连接顺序打印节点值
     */
    public static void printFlattenedList(TreeNode root) {
        System.out.print("展开后的链表: ");
        TreeNode current = root;

        // 当当前节点不为空时继续遍历
        while (current != null) {
            System.out.print(current.val);
            if (current.right != null) {
                System.out.print(" -> ");
            }
            current = current.right;
        }
        System.out.println();
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
     * 主函数：处理用户输入并演示二叉树展开为链表
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 打印构建的二叉树
     * 4. 调用三种方法展开二叉树
     * 5. 打印展开后的链表
     */
    public static void main(String[] args) {
        System.out.println("二叉树展开为链表");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息并退出
            System.out.println("创建的二叉树为空");
            return;
        }

        // 打印创建的二叉树
        System.out.println("原始二叉树:");
        printLevelOrder(root);

        // 方法1：递归解法
        // 创建解决方案实例
        Flatten114 solution = new Flatten114();
        // 保存原始树的副本用于后续方法测试
        TreeNode rootCopy1 = copyTree(root);
        TreeNode rootCopy2 = copyTree(root);

        solution.flatten(root);
        // 打印结果
        System.out.println("\n递归方法展开后:");
        printFlattenedList(root);

        // 方法2：迭代解法
        solution.flattenIterative(rootCopy1);
        // 打印结果
        System.out.println("\n迭代方法展开后:");
        printFlattenedList(rootCopy1);

        // 方法3：原地解法
        solution.flattenInPlace(rootCopy2);
        // 打印结果
        System.out.println("\n原地方法展开后:");
        printFlattenedList(rootCopy2);
    }

    /**
     * 辅助方法：复制二叉树
     */
    private static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = copyTree(root.left);
        newRoot.right = copyTree(root.right);
        return newRoot;
    }
}
