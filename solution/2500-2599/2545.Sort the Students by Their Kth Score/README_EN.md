# [2545. Sort the Students by Their Kth Score](https://leetcode.com/problems/sort-the-students-by-their-kth-score)

[中文文档](/solution/2500-2599/2545.Sort%20the%20Students%20by%20Their%20Kth%20Score/README.md)

## Description

<p>There is a class with <code>m</code> students and <code>n</code> exams. You are given a <strong>0-indexed</strong> <code>m x n</code> integer matrix <code>score</code>, where each row represents one student and <code>score[i][j]</code> denotes the score the <code>i<sup>th</sup></code> student got in the <code>j<sup>th</sup></code> exam. The matrix <code>score</code> contains <strong>distinct</strong> integers only.</p>

<p>You are also given an integer <code>k</code>. Sort the students (i.e., the rows of the matrix) by their scores in the <code>k<sup>th</sup></code>&nbsp;(<strong>0-indexed</strong>) exam from the highest to the lowest.</p>

<p>Return <em>the matrix after sorting it.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2545.Sort%20the%20Students%20by%20Their%20Kth%20Score/images/example1.png" style="width: 600px; height: 136px;" />
<pre>
<strong>Input:</strong> score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]], k = 2
<strong>Output:</strong> [[7,5,11,2],[10,6,9,1],[4,8,3,15]]
<strong>Explanation:</strong> In the above diagram, S denotes the student, while E denotes the exam.
- The student with index 1 scored 11 in exam 2, which is the highest score, so they got first place.
- The student with index 0 scored 9 in exam 2, which is the second highest score, so they got second place.
- The student with index 2 scored 3 in exam 2, which is the lowest score, so they got third place.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2545.Sort%20the%20Students%20by%20Their%20Kth%20Score/images/example2.png" style="width: 486px; height: 121px;" />
<pre>
<strong>Input:</strong> score = [[3,4],[5,6]], k = 0
<strong>Output:</strong> [[5,6],[3,4]]
<strong>Explanation:</strong> In the above diagram, S denotes the student, while E denotes the exam.
- The student with index 1 scored 5 in exam 0, which is the highest score, so they got first place.
- The student with index 0 scored 3 in exam 0, which is the lowest score, so they got second place.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == score.length</code></li>
	<li><code>n == score[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 250</code></li>
	<li><code>1 &lt;= score[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>score</code> consists of <strong>distinct</strong> integers.</li>
	<li><code>0 &lt;= k &lt; n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortTheStudents(self, score: List[List[int]], k: int) -> List[List[int]]:
        return sorted(score, key=lambda x: -x[k])
```

### **Java**

```java
class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> b[k] - a[k]);
        return score;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> sortTheStudents(vector<vector<int>>& score, int k) {
        sort(score.begin(), score.end(), [&](const auto& a, const auto& b) { return a[k] > b[k]; });
        return score;
    }
};
```

### **Go**

```go
func sortTheStudents(score [][]int, k int) [][]int {
	sort.Slice(score, func(i, j int) bool { return score[i][k] > score[j][k] })
	return score
}
```

### **TypeScript**

```ts
function sortTheStudents(score: number[][], k: number): number[][] {
    return score.sort((a, b) => b[k] - a[k]);
}
```

### **Rust**

```rust
impl Solution {
    pub fn sort_the_students(mut score: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let k = k as usize;
        score.sort_by(|a, b| b[k].cmp(&a[k]));
        score
    }
}
```

### **...**

```

```

<!-- tabs:end -->
