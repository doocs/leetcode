package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 路径总和III（LeetCode 437）
 *
 * 时间复杂度：O(n²)
 * - n是二叉树中的节点数
 * - 对于每个节点，都需要向下遍历其子树
 *
 * 空间复杂度：O(h)
 * - h是二叉树的高度
 * - 递归调用栈的深度
 */
public class PathSum437 {

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
     * 计算路径总和等于目标值的路径数量
     *
     * 算法思路：
     * 对于每个节点，我们计算以该节点为起点的路径中和为targetSum的路径数量
     * 然后递归地对所有节点执行此操作
     *
     * 执行过程分析（以二叉树 [10,5,-3,3,2,null,11,3,-2,null,1]，targetSum=8 为例）：
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *
     * 满足条件的路径：
     * 1. 5 -> 3 (和为8)
     * 2. 5 -> 2 -> 1 (和为8)
     * 3. -3 -> 11 (和为8)
     *
     * 总共3条路径
     *
     * 时间复杂度分析：
     * - 对每个节点调用 [pathSumFrom](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/PathSum437.java#L101-L124) 方法：O(n)
     * - 每次 [pathSumFrom](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/PathSum437.java#L101-L124) 调用需要遍历子树：O(n)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，h为树的高度
     *
     * @param root 二叉树的根节点
     * @param targetSum 目标和
     * @return 路径数量
     */
    public int pathSum(TreeNode root, int targetSum) {
        // 如果根节点为空，返回0
        if (root == null) {
            return 0;
        }

        // 计算以当前节点为起点的路径数量 + 递归计算左右子树的路径数量
        return pathSumFrom(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    /**
     * 计算以指定节点为起点的路径中和为targetSum的路径数量
     *
     * 算法思路：
     * 从当前节点开始向下遍历，累加路径和，如果等于目标和则计数加1
     *
     * 时间复杂度分析：
     * - 遍历以node为根的子树：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param node 当前节点
     * @param targetSum 剩余目标和
     * @return 路径数量
     */
    private int pathSumFrom(TreeNode node, long targetSum) {
        // 如果节点为空，返回0
        if (node == null) {
            return 0;
        }

        // 检查当前节点值是否等于剩余目标和
        // 如果等于，则找到一条路径，计数为1，否则为0
        int count = (node.val == targetSum) ? 1 : 0;

        // 递归计算左右子树中满足条件的路径数量
        return count + pathSumFrom(node.left, targetSum - node.val) + pathSumFrom(node.right, targetSum - node.val);
    }

    /**
     * 优化解法：使用前缀和
     *
     * 算法思路：
     * 使用HashMap记录从根节点到当前节点路径上的前缀和及其出现次数
     * 对于当前节点，检查是否存在前缀和等于当前前缀和减去目标和的节点
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     * - HashMap操作平均时间复杂度：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashMap存储前缀和：O(n)
     * - 递归调用栈：O(h)
     * - 总空间复杂度：O(n)
     *
     * @param root 二叉树的根节点
     * @param targetSum 目标和
     * @return 路径数量
     */
    public int pathSumOptimized(TreeNode root, int targetSum) {
        // 创建HashMap存储前缀和及其出现次数
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        // 初始化前缀和为0的出现次数为1
        prefixSumMap.put(0L, 1);

        // 调用递归辅助方法
        return pathSumHelper(root, 0, targetSum, prefixSumMap);
    }

    /**
     * 使用前缀和的递归辅助方法
     *
     * 算法思路：
     * 1. 计算当前节点的前缀和
     * 2. 检查是否存在前缀和等于当前前缀和减去目标和的节点
     * 3. 更新前缀和映射
     * 4. 递归处理左右子树
     * 5. 回溯时恢复前缀和映射
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     * - HashMap操作平均时间复杂度：O(1)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(h)
     *
     * @param node 当前节点
     * @param currentSum 当前前缀和
     * @param targetSum 目标和
     * @param prefixSumMap 前缀和映射
     * @return 路径数量
     */
    private int pathSumHelper(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumMap) {
        // 如果节点为空，返回0
        if (node == null) {
            return 0;
        }

        // 计算当前节点的前缀和
        currentSum += node.val;

        // 检查是否存在前缀和等于currentSum - targetSum的节点
        int count = prefixSumMap.getOrDefault(currentSum - targetSum, 0);

        // 更新前缀和映射
        prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);

        // 递归处理左右子树
        count += pathSumHelper(node.left, currentSum, targetSum, prefixSumMap);
        count += pathSumHelper(node.right, currentSum, targetSum, prefixSumMap);

        // 回溯时恢复前缀和映射
        prefixSumMap.put(currentSum, prefixSumMap.get(currentSum) - 1);

        return count;
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

        // while (!queue.isEmpty()) 当队列不为空时继续遍历
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
     * 主函数：处理用户输入并计算路径总和
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 提示用户输入目标和
     * 4. 调用两种方法计算路径数量
     * 5. 打印计算结果
     */
    public static void main(String[] args) {
        System.out.println("路径总和III");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息并退出
            System.out.println("创建的二叉树为空");
            return;
        }

        // 打印创建的二叉树
        System.out.println("创建的二叉树:");
        printLevelOrder(root);

        // 输入目标和
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入目标和
        System.out.print("请输入目标和: ");
        // 读取目标和
        int targetSum = scanner.nextInt();

        // 计算路径数量
        // 创建解决方案实例
        PathSum437 solution = new PathSum437();
        int result1 = solution.pathSum(root, targetSum);
        int result2 = solution.pathSumOptimized(root, targetSum);

        // 打印结果
        System.out.println("基础方法计算的路径数量: " + result1);
        System.out.println("优化方法计算的路径数量: " + result2);
    }
}
