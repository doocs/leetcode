package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 从前序与中序遍历序列构造二叉树（LeetCode 105）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：O(n)
 * -  hashmap存储中序遍历的索引映射
 * - 递归调用栈的深度最多为n
 */
public class BuildTree105 {

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

    // 用于存储中序遍历中节点值到索引的映射
    private Map<Integer, Integer> inorderMap;
    // 前序遍历的索引指针
    private int preorderIndex;

    /**
     * 根据前序遍历和中序遍历构建二叉树
     *
     * 算法思路：
     * 1. 前序遍历的第一个元素是根节点
     * 2. 在中序遍历中找到根节点的位置，将中序遍历分为左右两部分
     * 3. 递归地构建左子树和右子树
     *
     * 执行过程分析（以前序遍历[3,9,20,15,7]，中序遍历[9,3,15,20,7]为例）：
     *
     * 前序遍历: [3,  9,  20, 15, 7]
     *           root
     *
     * 中序遍历: [9,  3,  15, 20, 7]
     *           左子树  root  右子树
     *
     * 递归构建过程：
     * buildTree([3,9,20,15,7], [9,3,15,20,7])
     * ├─ 根节点为3，中序遍历中3的索引为1
     * ├─ 左子树: 前序[9], 中序[9] -> 构建节点9
     * └─ 右子树: 前序[20,15,7], 中序[15,20,7]
     *    ├─ 根节点为20，中序遍历中20的索引为3
     *    ├─ 左子树: 前序[15], 中序[15] -> 构建节点15
     *    └─ 右子树: 前序[7], 中序[7] -> 构建节点7
     *
     * 构建的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 时间复杂度分析：
     * - 构建hashmap：O(n)
     * - 递归构建树：O(n)，每个节点访问一次
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - hashmap存储：O(n)
     * - 递归调用栈：O(h)，h为树的高度，最坏情况O(n)
     * - 总空间复杂度：O(n)
     *
     * @param preorder 前序遍历序列
     * @param inorder 中序遍历序列
     * @return 构建的二叉树根节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 初始化中序遍历的索引映射
        inorderMap = new HashMap<>();
        preorderIndex = 0;

        // 构建中序遍历中节点值到索引的映射
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // 调用递归辅助方法构建二叉树
        return buildTreeHelper(0, inorder.length - 1, preorder);
    }

    /**
     * 递归辅助方法
     *
     * 算法思路：
     * 1. 如果左边界大于右边界，返回null
     * 2. 取前序遍历当前索引的值作为根节点
     * 3. 在中序遍历中找到根节点位置，分割左右子树
     * 4. 递归构建左右子树
     *
     * 时间复杂度分析：
     * - 每个节点处理一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(h)，h为树的高度
     *
     * @param left 中序遍历的左边界
     * @param right 中序遍历的右边界
     * @param preorder 前序遍历序列
     * @return 构建的子树根节点
     */
    private TreeNode buildTreeHelper(int left, int right, int[] preorder) {
        // 基础情况：如果左边界大于右边界，返回null
        if (left > right) {
            return null;
        }

        // 取前序遍历当前索引的值作为根节点
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        // 在中序遍历中找到根节点位置
        int rootIndex = inorderMap.get(rootVal);

        // 递归构建左子树：范围是[left, rootIndex - 1]
        root.left = buildTreeHelper(left, rootIndex - 1, preorder);

        // 递归构建右子树：范围是[rootIndex + 1, right]
        root.right = buildTreeHelper(rootIndex + 1, right, preorder);

        return root;
    }

    /**
     * 另一种实现方式：不使用全局变量
     *
     * 时间复杂度分析：
     * - 与主方法相同：O(n)
     *
     * 空间复杂度分析：
     * - 与主方法相同：O(n)
     *
     * @param preorder 前序遍历序列
     * @param inorder 中序遍历序列
     * @return 构建的二叉树根节点
     */
    public TreeNode buildTreeAlternative(int[] preorder, int[] inorder) {
        // 创建中序遍历的索引映射
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        // 调用递归辅助方法
        return buildTreeHelperAlternative(preorder, 0, inorder, 0, inorder.length - 1, map);
    }

    /**
     * 不使用全局变量的递归辅助方法
     *
     * 算法思路：
     * 通过传递索引参数而非使用全局变量来跟踪构建进度
     *
     * 时间复杂度分析：
     * - 每个节点处理一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(h)，h为树的高度
     *
     * @param preorder 前序遍历序列
     * @param preStart 前序遍历的起始索引
     * @param inorder 中序遍历序列
     * @param inStart 中序遍历的起始索引
     * @param inEnd 中序遍历的结束索引
     * @param map 中序遍历的索引映射
     * @return 构建的子树根节点
     */
    private TreeNode buildTreeHelperAlternative(int[] preorder, int preStart, int[] inorder,
                                               int inStart, int inEnd, Map<Integer, Integer> map) {
        // 基础情况：如果索引越界，返回null
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(preorder[preStart]);

        // 在中序遍历中找到根节点位置
        int rootIndex = map.get(root.val);

        // 递归构建左子树
        // 左子树的前序遍历范围：[preStart + 1, preStart + rootIndex - inStart]
        // 左子树的中序遍历范围：[inStart, rootIndex - 1]
        root.left = buildTreeHelperAlternative(preorder, preStart + 1, inorder,
                                             inStart, rootIndex - 1, map);

        // 递归构建右子树
        // 右子树的前序遍历范围：[preStart + rootIndex - inStart + 1, preEnd]
        // 右子树的中序遍历范围：[rootIndex + 1, inEnd]
        root.right = buildTreeHelperAlternative(preorder, preStart + rootIndex - inStart + 1,
                                              inorder, rootIndex + 1, inEnd, map);

        return root;
    }

    /**
     * 辅助方法：读取用户输入的遍历序列
     *
     * 算法思路：
     * 1. 提示用户输入遍历序列
     * 2. 按空格分割输入字符串
     * 3. 将字符串数组转换为整数数组
     *
     * 时间复杂度分析：
     * - 处理输入字符串：O(m)，m为输入字符数
     * - 转换为整数：O(n)，n为元素个数
     *
     * 空间复杂度分析：
     * - 存储字符串数组：O(m)
     * - 存储整数数组：O(n)
     *
     * @param prompt 提示信息
     * @return 用户输入的整数数组
     */
    private static int[] readTraversalSequence(String prompt) {
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入
        System.out.println(prompt);
        // 读取一行输入
        String input = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] strArray = input.split(" ");

        // 创建整数数组
        int[] nums = new int[strArray.length];
        // for (int i = 0; i < strArray.length; i++) 遍历字符串数组
        for (int i = 0; i < strArray.length; i++) {
            // nums[i] = Integer.parseInt(strArray[i]) 将字符串转换为整数
            nums[i] = Integer.parseInt(strArray[i]);
        }

        // return nums 返回整数数组
        return nums;
    }

    /**
     * 辅助方法：前序遍历打印树结构
     *
     * 算法思路：
     * 按照根-左-右的顺序遍历二叉树并打印节点值
     *
     * 时间复杂度分析：
     * - 访问每个节点一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(h)
     */
    public static void printPreorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    /**
     * 辅助方法：中序遍历打印树结构
     *
     * 算法思路：
     * 按照左-根-右的顺序遍历二叉树并打印节点值
     *
     * 时间复杂度分析：
     * - 访问每个节点一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(h)
     */
    public static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }

    /**
     * 辅助方法：层序遍历打印二叉树
     *
     * 算法思路：
     * 使用队列进行层序遍历，依次打印每个节点的值
     *
     * 时间复杂度分析：
     * - 访问每个节点一次：O(n)
     *
     * 空间复杂度分析：
     * - 队列存储节点：O(w)，w为树的最大宽度
     */
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }

        // 创建队列用于层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);
        // 打印提示信息
        System.out.print("层序遍历结果: ");

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
     * 主函数：处理用户输入并演示从前序与中序遍历序列构造二叉树
     *
     * 程序执行流程：
     * 1. 提示用户输入前序遍历序列
     * 2. 提示用户输入中序遍历序列
     * 3. 调用两种方法构建二叉树
     * 4. 打印构建的二叉树
     */
    public static void main(String[] args) {
        System.out.println("从前序与中序遍历序列构造二叉树");

        // 读取前序遍历序列
        int[] preorder = readTraversalSequence("请输入前序遍历序列，以空格分隔：");

        // 读取中序遍历序列
        int[] inorder = readTraversalSequence("请输入中序遍历序列，以空格分隔：");

        // 打印输入的遍历序列
        System.out.println("输入的前序遍历序列: " + Arrays.toString(preorder));
        System.out.println("输入的中序遍历序列: " + Arrays.toString(inorder));

        // 构建二叉树
        // 创建解决方案实例
        BuildTree105 solution = new BuildTree105();
        TreeNode root1 = solution.buildTree(preorder, inorder);
        TreeNode root2 = solution.buildTreeAlternative(preorder, inorder);

        // 打印结果
        System.out.println("\n方法1构建的二叉树:");
        printLevelOrder(root1);
        System.out.print("前序遍历验证: ");
        printPreorder(root1);
        System.out.println();
        System.out.print("中序遍历验证: ");
        printInorder(root1);
        System.out.println();

        System.out.println("\n方法2构建的二叉树:");
        printLevelOrder(root2);
        System.out.print("前序遍历验证: ");
        printPreorder(root2);
        System.out.println();
        System.out.print("中序遍历验证: ");
        printInorder(root2);
        System.out.println();
    }
}
