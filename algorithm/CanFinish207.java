package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 课程表（LeetCode 207）
 *
 * 时间复杂度：O(V + E)
 * - V是课程数量（顶点数），E是先决条件数量（边数）
 * - 需要遍历所有顶点和边
 *
 * 空间复杂度：O(V + E)
 * - 邻接表存储图需要O(E)空间
 * - 入度数组和队列需要O(V)空间
 */
public class CanFinish207 {

    /**
     * 判断是否可以完成所有课程
     *
     * 算法思路：
     * 使用拓扑排序（Kahn算法）检测有向图中是否存在环
     * 1. 构建邻接表表示课程依赖关系
     * 2. 计算每门课程的入度（先修课程数量）
     * 3. 将入度为0的课程加入队列（可以立即学习的课程）
     * 4. 不断从队列中取出课程，将其后继课程的入度减1
     * 5. 如果后继课程入度变为0，加入队列
     * 6. 最后检查处理的课程数量是否等于总课程数
     *
     * 执行过程分析（以numCourses=4, prerequisites=[[1,0],[2,0],[3,1],[3,2]]为例）：
     *
     * 构建邻接表和入度数组：
     * 邻接表：0->[1,2], 1->[3], 2->[3], 3->[]
     * 入度数组：[0,1,1,2]（课程0入度0，课程1入度1，课程2入度1，课程3入度2）
     *
     * 拓扑排序过程：
     * 1. 初始队列：[0]（入度为0的课程）
     *    处理课程0：入度数组变为[0,0,0,2]，队列变为[1,2]
     *
     * 2. 队列：[1,2]
     *    处理课程1：入度数组变为[0,0,0,1]，队列变为[2,3]
     *    处理课程2：入度数组变为[0,0,0,0]，队列变为[3]
     *
     * 3. 队列：[3]
     *    处理课程3：入度数组变为[0,0,0,0]，队列变为[]
     *
     * 4. 队列为空，处理了4门课程 = 总课程数4
     *    返回true（可以完成所有课程）
     *
     * @param numCourses 课程总数
     * @param prerequisites 先决条件数组，prerequisites[i] = [ai, bi]表示学习课程ai前必须完成课程bi
     * @return 如果可以完成所有课程返回true，否则返回false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 边界条件检查：没有课程或没有先决条件
        if (numCourses <= 0) {
            return true;
        }

        // 构建邻接表：graph[i]表示课程i的后续课程列表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 计算每门课程的入度（先修课程数量）
        int[] inDegree = new int[numCourses];

        // 遍历所有先决条件，构建图和入度数组
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];      // 当前课程
            int prerequisiteCourse = prerequisite[1];  // 先修课程

            // 添加边：先修课程 -> 当前课程
            graph.get(prerequisiteCourse).add(course);

            // 增加当前课程的入度
            inDegree[course]++;
        }

        // 创建队列，存储入度为0的课程（可以立即学习的课程）
        Queue<Integer> queue = new LinkedList<>();

        // 将所有入度为0的课程加入队列
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 记录已处理的课程数量
        int processedCourses = 0;

        // 拓扑排序过程
        while (!queue.isEmpty()) {
            // 从队列中取出一个可以学习的课程
            int currentCourse = queue.poll();

            // 增加已处理课程数量
            processedCourses++;

            // 遍历当前课程的所有后续课程
            for (int nextCourse : graph.get(currentCourse)) {
                // 减少后续课程的入度（相当于完成了一门先修课程）
                inDegree[nextCourse]--;

                // 如果后续课程的入度变为0，说明可以学习了
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 如果处理的课程数量等于总课程数，说明可以完成所有课程
        // 否则说明存在循环依赖，无法完成所有课程
        return processedCourses == numCourses;
    }

    /**
     * 方法2：使用深度优先搜索（DFS）检测环
     *
     * 算法思路：
     * 使用三色标记法检测有向图中的环
     * 0: 未访问
     * 1: 正在访问（在当前DFS路径中）
     * 2: 已完成访问
     *
     * @param numCourses 课程总数
     * @param prerequisites 先决条件数组
     * @return 如果可以完成所有课程返回true，否则返回false
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // 边界条件检查
        if (numCourses <= 0) {
            return true;
        }

        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 填充邻接表
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        // 0: 未访问, 1: 正在访问, 2: 已完成访问
        int[] visited = new int[numCourses];

        // 对每个未访问的课程进行DFS
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(graph, visited, i)) {
                    return false; // 发现环，无法完成所有课程
                }
            }
        }

        return true; // 没有发现环，可以完成所有课程
    }

    /**
     * DFS辅助方法：检测是否存在环
     *
     * @param graph 邻接表
     * @param visited 访问状态数组
     * @param course 当前课程
     * @return 如果存在环返回true，否则返回false
     */
    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        // 如果当前课程正在访问中，说明发现了环
        if (visited[course] == 1) {
            return true;
        }

        // 如果当前课程已完成访问，说明之前已检查过，无环
        if (visited[course] == 2) {
            return false;
        }

        // 标记当前课程为正在访问
        visited[course] = 1;

        // 递归访问所有后续课程
        for (int nextCourse : graph.get(course)) {
            if (hasCycle(graph, visited, nextCourse)) {
                return true;
            }
        }

        // 标记当前课程为已完成访问
        visited[course] = 2;

        return false;
    }

    /**
     * 辅助方法：读取用户输入的课程数和先决条件
     *
     * @return 包含课程数和先决条件数组的数组
     */
    public static Object[] readInput() {
        Scanner scanner = new Scanner(System.in);

        // 读取课程总数
        System.out.print("请输入课程总数: ");
        int numCourses = scanner.nextInt();

        // 读取先决条件数量
        System.out.print("请输入先决条件数量: ");
        int preCount = scanner.nextInt();

        // 读取先决条件
        int[][] prerequisites = new int[preCount][2];
        System.out.println("请输入先决条件（格式：课程编号 先修课程编号）：");
        for (int i = 0; i < preCount; i++) {
            prerequisites[i][0] = scanner.nextInt(); // 课程编号
            prerequisites[i][1] = scanner.nextInt(); // 先修课程编号
        }

        return new Object[]{numCourses, prerequisites};
    }

    /**
     * 辅助方法：打印先决条件
     *
     * @param prerequisites 先决条件数组
     */
    public static void printPrerequisites(int[][] prerequisites) {
        System.out.println("先决条件：");
        for (int[] pre : prerequisites) {
            System.out.println("学习课程 " + pre[0] + " 前必须完成课程 " + pre[1]);
        }
    }

    /**
     * 主函数：处理用户输入并判断是否可以完成所有课程
     */
    public static void main(String[] args) {
        System.out.println("课程表问题");

        // 读取用户输入
        Object[] input = readInput();
        int numCourses = (int) input[0];
        int[][] prerequisites = (int[][]) input[1];

        // 打印输入信息
        System.out.println("课程总数: " + numCourses);
        printPrerequisites(prerequisites);

        // 创建解决方案对象
        CanFinish207 solution = new CanFinish207();

        // 使用BFS方法判断
        boolean result1 = solution.canFinish(numCourses, prerequisites);

        // 使用DFS方法判断
        boolean result2 = solution.canFinishDFS(numCourses, prerequisites);

        // 输出结果
        System.out.println("BFS方法结果: " + (result1 ? "可以完成所有课程" : "无法完成所有课程"));
        System.out.println("DFS方法结果: " + (result2 ? "可以完成所有课程" : "无法完成所有课程"));
    }
}
