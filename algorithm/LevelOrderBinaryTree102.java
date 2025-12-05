package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 二叉树的层序遍历（LeetCode 102）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：O(w)
 * - w是二叉树的最大宽度
 * - 队列最多存储一层的所有节点
 */
public class LevelOrderBinaryTree102 {

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
     * 二叉树的层序遍历
     *
     * 算法思路：
     * 使用队列进行广度优先搜索（BFS）
     * 1. 将根节点加入队列
     * 2. 当队列不为空时，记录当前层的节点数
     * 3. 依次处理当前层的所有节点，并将下一层的节点加入队列
     * 4. 重复步骤2-3直到队列为空
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
     * 初始状态：队列=[3]，结果=[]
     *
     * 第1层处理：
     * - size=1
     * - 弹出3，加入结果[[3]]，将9和20加入队列
     * - 队列=[9,20]
     *
     * 第2层处理：
     * - size=2
     * - 弹出9，加入结果[[3],[9]]，9无子节点
     * - 弹出20，加入结果[[3],[9,20]]，将15和7加入队列
     * - 队列=[15,7]
     *
     * 第3层处理：
     * - size=2
     * - 弹出15，加入结果[[3],[9,20],[15]]，15无子节点
     * - 弹出7，加入结果[[3],[9,20],[15,7]]，7无子节点
     * - 队列=[]
     *
     * 返回结果：[[3],[9,20],[15,7]]
     *
     * 时间复杂度分析：
     * - 每个节点入队和出队一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 队列最多存储一层的节点数：O(w)，其中w为树的最大宽度
     * - 结果列表存储所有节点：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param root 二叉树的根节点
     * @return 按层序排列的节点值列表
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 创建结果列表，用于存储每层的节点值
        List<List<Integer>> result = new ArrayList<>();

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

            // 存储当前层的节点值
            List<Integer> currentLevel = new ArrayList<>();

            // 处理当前层的所有节点
            for (int i = 0; i < size; i++) {
                // 弹出队列中的节点
                TreeNode node = queue.poll();

                // 将节点值加入当前层结果
                currentLevel.add(node.val);

                // 将下一层的节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // 将当前层结果加入最终结果
            result.add(currentLevel);
        }

        return result;
    }


    /**
     * 递归解法
     *
     * 算法思路：
     * 使用深度优先搜索（DFS），通过层数参数来确定节点应该加入哪一层的结果列表
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
     * levelOrderHelper(3, 0, result)
     * ├─ result.size() <= 0 -> 创建新列表，result=[[3]]
     * ├─ levelOrderHelper(9, 1, result)
     * │  ├─ result.size() <= 1 -> 创建新列表，result=[[3],[9]]
     * │  ├─ levelOrderHelper(null, 2, result) -> 返回
     * │  └─ levelOrderHelper(null, 2, result) -> 返回
     * ├─ levelOrderHelper(20, 1, result)
     * │  ├─ result.size() = 2 > 1 -> 直接添加，result=[[3],[9,20]]
     * │  ├─ levelOrderHelper(15, 2, result)
     * │  │  ├─ result.size() <= 2 -> 创建新列表，result=[[3],[9,20],[15]]
     * │  │  └─ ...
     * │  └─ levelOrderHelper(7, 2, result)
     * │     ├─ result.size() = 3 > 2 -> 直接添加，result=[[3],[9,20],[15,7]]
     * │     └─ ...
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)，其中n为二叉树节点数
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     * - 结果列表存储所有节点：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param root 二叉树的根节点
     * @return 按层序排列的节点值列表
     */
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        // 创建结果列表，用于存储每层的节点值
        List<List<Integer>> result = new ArrayList<>();
        levelOrderHelper(root, 0, result);
        return result;
    }

    /**
     * 递归辅助方法
     *
     * 算法思路：
     * 1. 如果节点为空，直接返回
     * 2. 如果结果列表的大小小于等于当前层数，为当前层创建新的列表
     * 3. 将当前节点值加入对应层的列表
     * 4. 递归处理左右子树，层数加1
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     *
     * @param node 当前节点
     * @param level 当前层数
     * @param result 结果列表
     */
    private void levelOrderHelper(TreeNode node, int level, List<List<Integer>> result) {
        // 基础情况：如果节点为空，直接返回
        if (node == null) {
            return;
        }

        // 如果当前层还没有对应的列表，创建一个新的列表
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }

        // 将当前节点值加入对应层的列表
        result.get(level).add(node.val);

        // 递归处理左右子树，层数加1
        levelOrderHelper(node.left, level + 1, result);
        levelOrderHelper(node.right, level + 1, result);
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
     * 辅助方法：打印层序遍历结果
     *
     * 算法思路：
     * 遍历结果列表，按层打印每层的节点值
     */
    public static void printLevelOrderResult(List<List<Integer>> result) {
        System.out.println("层序遍历结果:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("第" + (i + 1) + "层: " + result.get(i));
        }
    }

    /**
     * 主函数：处理用户输入并执行层序遍历
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 打印构建成功的提示信息
     * 4. 调用两种方法执行层序遍历
     * 5. 打印遍历结果
     */
    public static void main(String[] args) {
        System.out.println("二叉树层序遍历");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息并退出
            System.out.println("创建的二叉树为空");
            return;
        }

        System.out.println("二叉树创建成功");

        // 执行层序遍历
        // 创建解决方案实例
        LevelOrderBinaryTree102 solution = new LevelOrderBinaryTree102();
        List<List<Integer>> result1 = solution.levelOrder(root);
        List<List<Integer>> result2 = solution.levelOrderRecursive(root);

        // 打印结果
        System.out.println("\n迭代方法结果:");
        printLevelOrderResult(result1);

        System.out.println("\n递归方法结果:");
        printLevelOrderResult(result2);
    }
}
