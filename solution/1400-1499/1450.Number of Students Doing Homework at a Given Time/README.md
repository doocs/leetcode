# [1450. 在既定时间做作业的学生人数](https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time)

[English Version](/solution/1400-1499/1450.Number%20of%20Students%20Doing%20Homework%20at%20a%20Given%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组 <code>startTime</code>（开始时间）和 <code>endTime</code>（结束时间），并指定一个整数 <code>queryTime</code> 作为查询时间。</p>

<p>已知，第 <code>i</code> 名学生在 <code>startTime[i]</code> 时开始写作业并于 <code>endTime[i]</code> 时完成作业。</p>

<p>请返回在查询时间 <code>queryTime</code> 时正在做作业的学生人数。形式上，返回能够使 <code>queryTime</code> 处于区间 <code>[startTime[i], endTime[i]]</code>（含）的学生人数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
<strong>输出：</strong>1
<strong>解释：</strong>一共有 3 名学生。
第一名学生在时间 1 开始写作业，并于时间 3 完成作业，在时间 4 没有处于做作业的状态。
第二名学生在时间 2 开始写作业，并于时间 2 完成作业，在时间 4 没有处于做作业的状态。
第三名学生在时间 3 开始写作业，预计于时间 7 完成作业，这是是唯一一名在时间 4 时正在做作业的学生。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>startTime = [4], endTime = [4], queryTime = 4
<strong>输出：</strong>1
<strong>解释：</strong>在查询时间只有一名学生在做作业。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>startTime = [4], endTime = [4], queryTime = 5
<strong>输出：</strong>0
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>startTime = [1,1,1,1], endTime = [1,3,2,4], queryTime = 7
<strong>输出：</strong>0
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>startTime = [9,8,7,6,5,4,3,2,1], endTime = [10,10,10,10,10,10,10,10,10], queryTime = 5
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>startTime.length == endTime.length</code></li>
	<li><code>1 &lt;= startTime.length &lt;= 100</code></li>
	<li><code>1 &lt;= startTime[i] &lt;= endTime[i] &lt;= 1000</code></li>
	<li><code>1 &lt;=&nbsp;queryTime &lt;= 1000</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def busyStudent(self, startTime: List[int], endTime: List[int], queryTime: int) -> int:
        count, n = 0, len(startTime)
        for i in range(n):
            count += startTime[i] <= queryTime <= endTime[i]
        return count
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0, n = startTime.length;
        for (int i = 0; i < n; ++i) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ++count;
            }
        }
        return count;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int busyStudent(vector<int>& startTime, vector<int>& endTime, int queryTime) {
        int count = 0, n = startTime.size();
        for (int i = 0; i < n; ++i) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ++count;
            }
        }
        return count;
    }
};
```

### **Go**

```go
func busyStudent(startTime []int, endTime []int, queryTime int) int {
	count, n := 0, len(startTime)
	for i := 0; i < n; i++ {
		if startTime[i] <= queryTime && queryTime <= endTime[i] {
			count++
		}
	}
	return count
}
```

### **...**

```

```

<!-- tabs:end -->
