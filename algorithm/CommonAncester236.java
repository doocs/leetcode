package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最近公共祖先（LeetCode 236）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 最坏情况下需要遍历所有节点
 *
 * 空间复杂度：O(h)
 * - h是二叉树的高度
 * - 递归调用栈的深度
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class CommonAncester236 {

    public static void main(String[] args) {
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入二叉树节点和p, q的值
        // 读取二叉树节点数据
        String nodeList = scanner.nextLine();
        // 读取节点p的值
        int pVal = scanner.nextInt();
        // 读取节点q的值
        int qVal = scanner.nextInt();

        // 构造二叉树
        TreeNode root = buildTree(nodeList);
        TreeNode p = findNode(root, pVal);
        TreeNode q = findNode(root, qVal);

        // 查找最近公共祖先
        TreeNode lca = lowestCommonAncestor(root, p, q);
        System.out.println((lca != null ? lca.val : "null"));
    }

    /**
     * 解析输入的字符串并构建二叉树
     *
     * 算法思路：
     * 按层序遍历的方式构建二叉树
     * 使用队列来维护当前需要处理子节点的节点
     *
     * 时间复杂度分析：
     * - 遍历所有节点一次：O(n)，其中n为节点数
     *
     * 空间复杂度分析：
     * - 队列最多存储一层的节点数：O(w)，其中w为树的最大宽度
     * - 存储节点值数组：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param data 二叉树节点数据字符串
     * @return 构建完成的二叉树根节点
     */
    private static TreeNode buildTree(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        // 去掉输入的括号和空格，并分割成数组
        String[] values = data.split(" ");
        if (values[0].equals("null")) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(values[0].trim()));
        // 创建队列用于构建二叉树
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // 数组索引，从1开始处理子节点
        int i = 1;
        while (i < values.length) {
            // 从队列中取出一个节点
            TreeNode current = queue.poll();

            // 构造左子树
            if (!values[i].trim().equals("null")) {
                current.left = new TreeNode(Integer.parseInt(values[i].trim()));
                queue.add(current.left);
            }
            i++;

            // 构造右子树
            if (i < values.length && !values[i].trim().equals("null")) {
                current.right = new TreeNode(Integer.parseInt(values[i].trim()));
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    /**
     * 在二叉树中查找指定值的节点
     *
     * 算法思路：
     * 使用递归方式在二叉树中查找指定值的节点
     *
     * 时间复杂度分析：
     * - 最坏情况下需要遍历所有节点：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     *
     * @param root 二叉树根节点
     * @param val 目标节点值
     * @return 找到的节点，未找到返回null
     */
    private static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;

        TreeNode left = findNode(root.left, val);
        if (left != null) return left;

        return findNode(root.right, val);
    }

    /**
     * 查找二叉树中两个节点的最近公共祖先
     *
     * 算法思路：
     * 使用递归方式查找最近公共祖先
     * 对于每个节点，如果它是p或q，则返回该节点
     * 如果左子树和右子树分别包含p和q，则当前节点是最近公共祖先
     * 如果只有左子树或右子树包含p或q，则返回包含的那个子树的结果
     *
     * 执行过程分析（以二叉树 [3,5,1,6,2,0,8,null,null,7,4]，p=5, q=1 为例）：
     *
     *        3
     *      /   \
     *     5     1
     *    / \   / \
     *   6   2 0   8
     *      / \
     *     7   4
     *
     * 查找过程：
     * 1. 从根节点3开始，p=5在左子树，q=1在右子树
     * 2. 左子树返回5，右子树返回1
     * 3. 因为左右子树都返回非空结果，所以3是最近公共祖先
     *
     * 时间复杂度分析：
     * - 每个节点最多访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     *
     * @param root 二叉树根节点
     * @param p 目标节点p
     * @param q 目标节点q
     * @return 最近公共祖先节点
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 基础情况：节点为空或为p或q
        if (root == null || root == p || root == q) return root;

        // 在左子树中查找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 在右子树中查找
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树都找到节点
        if (left != null && right != null) return root;

        // 返回非空的结果
        return left != null ? left : right;
    }
}
