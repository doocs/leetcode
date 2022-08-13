# [2358. 分组的最大数量](https://leetcode.cn/problems/maximum-number-of-groups-entering-a-competition)

[English Version](/solution/2300-2399/2358.Maximum%20Number%20of%20Groups%20Entering%20a%20Competition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>grades</code> ，表示大学中一些学生的成绩。你打算将 <strong>所有</strong> 学生分为一些 <strong>有序</strong> 的非空分组，其中分组间的顺序满足以下全部条件：</p>

<ul>
	<li>第 <code>i</code> 个分组中的学生总成绩 <strong>小于</strong> 第 <code>(i + 1)</code> 个分组中的学生总成绩，对所有组均成立（除了最后一组）。</li>
	<li>第 <code>i</code> 个分组中的学生总数 <strong>小于</strong> 第 <code>(i + 1)</code> 个分组中的学生总数，对所有组均成立（除了最后一组）。</li>
</ul>

<p>返回可以形成的 <strong>最大</strong> 组数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>grades = [10,6,12,7,3,5]
<strong>输出：</strong>3
<strong>解释：</strong>下面是形成 3 个分组的一种可行方法：
- 第 1 个分组的学生成绩为 grades = [12] ，总成绩：12 ，学生数：1
- 第 2 个分组的学生成绩为 grades = [6,7] ，总成绩：6 + 7 = 13 ，学生数：2
- 第 3 个分组的学生成绩为 grades = [10,3,5] ，总成绩：10 + 3 + 5 = 18 ，学生数：3 
可以证明无法形成超过 3 个分组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grades = [8,8]
<strong>输出：</strong>1
<strong>解释：</strong>只能形成 1 个分组，因为如果要形成 2 个分组的话，会导致每个分组中的学生数目相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grades.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grades[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumGroups(self, grades: List[int]) -> int:
        grades.sort()
        ans = 1
        prev = [1, grades[0]]
        curr = [0, 0]
        for v in grades[1:]:
            curr[0] += 1
            curr[1] += v
            if prev[0] < curr[0] and prev[1] < curr[1]:
                prev = curr
                curr = [0, 0]
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumGroups(int[] grades) {
        Arrays.sort(grades);
        int ans = 1;
        int[] prev = new int[]{1, grades[0]};
        int[] curr = new int[]{0, 0};
        for (int i = 1; i < grades.length; ++i) {
            curr[0]++;
            curr[1] += grades[i];
            if (prev[0] < curr[0] && prev[1] < curr[1]) {
                prev = curr;
                curr = new int[]{0, 0};
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumGroups(vector<int>& grades) {
        sort(grades.begin(), grades.end());
        int ans = 1;
        vector<int> prev = {1, grades[0]};
        vector<int> curr = {0, 0};
        for (int i = 1; i < grades.size(); ++i) {
            curr[0]++;
            curr[1] += grades[i];
            if (prev[0] < curr[0] && prev[1] < curr[1]) {
                prev = curr;
                curr = {0, 0};
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumGroups(grades []int) int {
	sort.Ints(grades)
	ans := 1
	prev := []int{1, grades[0]}
	curr := []int{0, 0}
	for _, v := range grades[1:] {
		curr[0]++
		curr[1] += v
		if prev[0] < curr[0] && prev[1] < curr[1] {
			prev = curr
			curr = []int{0, 0}
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
