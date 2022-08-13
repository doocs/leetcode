# [1947. 最大兼容性评分和](https://leetcode.cn/problems/maximum-compatibility-score-sum)

[English Version](/solution/1900-1999/1947.Maximum%20Compatibility%20Score%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一份由 <code>n</code> 个问题组成的调查问卷，每个问题的答案要么是 <code>0</code>（no，否），要么是 <code>1</code>（yes，是）。</p>

<p>这份调查问卷被分发给 <code>m</code> 名学生和 <code>m</code> 名导师，学生和导师的编号都是从 <code>0</code> 到 <code>m - 1</code> 。学生的答案用一个二维整数数组 <code>students</code> 表示，其中 <code>students[i]</code> 是一个整数数组，包含第 <code>i</code> 名学生对调查问卷给出的答案（<strong>下标从 0 开始</strong>）。导师的答案用一个二维整数数组 <code>mentors</code> 表示，其中 <code>mentors[j]</code> 是一个整数数组，包含第 <code>j</code> 名导师对调查问卷给出的答案（<strong>下标从 0 开始</strong>）。</p>

<p>每个学生都会被分配给 <strong>一名</strong> 导师，而每位导师也会分配到 <strong>一名</strong> 学生。配对的学生与导师之间的兼容性评分等于学生和导师答案相同的次数。</p>

<ul>
	<li>例如，学生答案为<code>[1, <strong><em>0</em></strong>, <strong><em>1</em></strong>]</code> 而导师答案为 <code>[0, <strong><em>0</em></strong>, <strong><em>1</em></strong>]</code> ，那么他们的兼容性评分为 2 ，因为只有第二个和第三个答案相同。</li>
</ul>

<p>请你找出最优的学生与导师的配对方案，以 <strong>最大程度上</strong> 提高 <strong>兼容性评分和</strong> 。</p>

<p>给你 <code>students</code> 和 <code>mentors</code> ，返回可以得到的<em> </em><strong>最大兼容性评分和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
<strong>输出：</strong>8
<strong>解释：</strong>按下述方式分配学生和导师：
- 学生 0 分配给导师 2 ，兼容性评分为 3 。
- 学生 1 分配给导师 0 ，兼容性评分为 2 。
- 学生 2 分配给导师 1 ，兼容性评分为 3 。
最大兼容性评分和为 3 + 2 + 3 = 8 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]]
<strong>输出：</strong>0
<strong>解释：</strong>任意学生与导师配对的兼容性评分都是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == students.length == mentors.length</code></li>
	<li><code>n == students[i].length == mentors[j].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 8</code></li>
	<li><code>students[i][k]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>mentors[j][k]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxCompatibilitySum(
        self, students: List[List[int]], mentors: List[List[int]]
    ) -> int:
        def score(s, m):
            res = 0
            for i in range(len(s)):
                res += 1 if s[i] == m[i] else 0
            return res

        m, n = len(students), len(students[0])
        scores = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(m):
                scores[i][j] = score(students[i], mentors[j])
        p = self.permute(list(range(m)))
        mx = 0
        for item in p:
            t = 0
            sidx = 0
            for midx in item:
                t += scores[sidx][midx]
                sidx += 1
            mx = max(mx, t)
        return mx

    def permute(self, nums):
        def dfs(nums, i, res, path, used):
            if i == len(nums):
                res.append(copy.deepcopy(path))
                return
            for j in range(len(nums)):
                if not used[j]:
                    path.append(nums[j])
                    used[j] = True
                    dfs(nums, i + 1, res, path, used)
                    used[j] = False
                    path.pop()

        res, path = [], []
        used = [False] * len(nums)
        dfs(nums, 0, res, path, used)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length, n = students[0].length;
        int[][] scores = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                scores[i][j] = score(students[i], mentors[j]);
            }
        }
        int[] idx = new int[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        int mx = 0;
        List<List<Integer>> p = permute(idx);
        for (List<Integer> item : p) {
            int t = 0;
            int sidx = 0;
            for (int midx : item) {
                t += scores[sidx][midx];
                ++sidx;
            }
            mx = Math.max(mx, t);
        }
        return mx;
    }

    private int score(int[] s, int[] m) {
        int res = 0;
        for (int i = 0; i < s.length; ++i) {
            res += s[i] == m[i] ? 1 : 0;
        }
        return res;
    }

    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(res, nums, 0);
        return res;
    }

    private void permute(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> t = new ArrayList<>();
            for (int e : nums) {
                t.add(e);
            }
            res.add(t);
            return;
        }
        for (int i = start; i < nums.length; ++i) {
            swap(nums, i, start);
            permute(res, nums, start + 1);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
