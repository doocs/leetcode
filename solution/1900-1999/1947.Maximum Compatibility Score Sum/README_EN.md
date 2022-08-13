# [1947. Maximum Compatibility Score Sum](https://leetcode.com/problems/maximum-compatibility-score-sum)

[中文文档](/solution/1900-1999/1947.Maximum%20Compatibility%20Score%20Sum/README.md)

## Description

<p>There is a survey that consists of <code>n</code> questions where each question&#39;s answer is either <code>0</code> (no) or <code>1</code> (yes).</p>

<p>The survey was given to <code>m</code> students numbered from <code>0</code> to <code>m - 1</code> and <code>m</code> mentors numbered from <code>0</code> to <code>m - 1</code>. The answers of the students are represented by a 2D integer array <code>students</code> where <code>students[i]</code> is an integer array that contains the answers of the <code>i<sup>th</sup></code> student (<strong>0-indexed</strong>). The answers of the mentors are represented by a 2D integer array <code>mentors</code> where <code>mentors[j]</code> is an integer array that contains the answers of the <code>j<sup>th</sup></code> mentor (<strong>0-indexed</strong>).</p>

<p>Each student will be assigned to <strong>one</strong> mentor, and each mentor will have <strong>one</strong> student assigned to them. The <strong>compatibility score</strong> of a student-mentor pair is the number of answers that are the same for both the student and the mentor.</p>

<ul>
	<li>For example, if the student&#39;s answers were <code>[1, <u>0</u>, <u>1</u>]</code> and the mentor&#39;s answers were <code>[0, <u>0</u>, <u>1</u>]</code>, then their compatibility score is 2 because only the second and the third answers are the same.</li>
</ul>

<p>You are tasked with finding the optimal student-mentor pairings to <strong>maximize</strong> the<strong> sum of the compatibility scores</strong>.</p>

<p>Given <code>students</code> and <code>mentors</code>, return <em>the <strong>maximum compatibility score sum</strong> that can be achieved.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
<strong>Output:</strong> 8
<strong>Explanation:</strong>&nbsp;We assign students to mentors in the following way:
- student 0 to mentor 2 with a compatibility score of 3.
- student 1 to mentor 0 with a compatibility score of 2.
- student 2 to mentor 1 with a compatibility score of 3.
The compatibility score sum is 3 + 2 + 3 = 8.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The compatibility score of any student-mentor pair is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == students.length == mentors.length</code></li>
	<li><code>n == students[i].length == mentors[j].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 8</code></li>
	<li><code>students[i][k]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>mentors[j][k]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
