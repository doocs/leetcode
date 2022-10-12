# [1086. High Five](https://leetcode.com/problems/high-five)

[中文文档](/solution/1000-1099/1086.High%20Five/README.md)

## Description

<p>Given a list of the scores of different students, <code>items</code>, where <code>items[i] = [ID<sub>i</sub>, score<sub>i</sub>]</code> represents one score from a student with <code>ID<sub>i</sub></code>, calculate each student&#39;s <strong>top five average</strong>.</p>

<p>Return <em>the answer as an array of pairs </em><code>result</code><em>, where </em><code>result[j] = [ID<sub>j</sub>, topFiveAverage<sub>j</sub>]</code><em> represents the student with </em><code>ID<sub>j</sub></code><em> and their <strong>top five average</strong>. Sort </em><code>result</code><em> by </em><code>ID<sub>j</sub></code><em> in <strong>increasing order</strong>.</em></p>

<p>A student&#39;s <strong>top five average</strong> is calculated by taking the sum of their top five scores and dividing it by <code>5</code> using <strong>integer division</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
<strong>Output:</strong> [[1,87],[2,88]]
<strong>Explanation: </strong>
The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
<strong>Output:</strong> [[1,100],[7,100]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 1000</code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= ID<sub>i</sub> &lt;= 1000</code></li>
	<li><code>0 &lt;= score<sub>i</sub> &lt;= 100</code></li>
	<li>For each <code>ID<sub>i</sub></code>, there will be <strong>at least</strong> five scores.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def highFive(self, items: List[List[int]]) -> List[List[int]]:
        s = [None] * 101
        for i, score in items:
            if s[i] is None:
                s[i] = []
            s[i].append(score)
        res = []
        for i, scores in enumerate(s):
            if scores is None:
                continue
            avg = sum(nlargest(5, scores)) // 5
            res.append([i, avg])
        return res
```

### **Java**

```java
class Solution {
    public int[][] highFive(int[][] items) {
        int size = 0;
        PriorityQueue[] s = new PriorityQueue[101];
        int n = 5;
        for (int[] item : items) {
            int i = item[0], score = item[1];
            if (s[i] == null) {
                ++size;
                s[i] = new PriorityQueue<>(n);
            }
            s[i].offer(score);
            if (s[i].size() > n) {
                s[i].poll();
            }
        }
        int[][] res = new int[size][2];
        int j = 0;
        for (int i = 0; i < 101; ++i) {
            if (s[i] == null) {
                continue;
            }
            int avg = sum(s[i]) / n;
            res[j][0] = i;
            res[j++][1] = avg;
        }
        return res;
    }

    private int sum(PriorityQueue<Integer> q) {
        int s = 0;
        while (!q.isEmpty()) {
            s += q.poll();
        }
        return s;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
