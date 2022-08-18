# [1450. 在既定时间做作业的学生人数](https://leetcode.cn/problems/number-of-students-doing-homework-at-a-given-time)

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

**方法一：遍历计数**

同时遍历 $startTime$ 和 $endTime$，统计正在做作业的学生人数。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是 $startTime$ 和 $endTime$ 的长度。

**方法二：差分数组**

差分数组可以 $O(1)$ 时间处理区间加减操作。例如，对区间 $[l, r]$ 中的每个数加上 $c$。

假设数组 $a$ 的所有元素分别为 $a[1], a[2], ... a[n]$，则差分数组 $b$ 的元素 $b[i]=a[i]-a[i-1]$。

$$
\begin{cases}
b[1]=a[1]\\
b[2]=a[2]-a[1]\\
b[3]=a[3]-a[2]\\
...\\
b[i]=a[i]-a[i-1]\\
\end{cases}
$$

那么 $a[i]=b[1]+b[2]+...+b[i]$，原数组 $a$ 是差分数组 $b$ 的前缀和。

在这道题中，我们定义差分数组 $c$，然后遍历两个数组中对应位置的两个数 $a$, $b$，则 $c[a]+=1$, $c[b+1]-=1$。

遍历结束后，对差分数组 $c$ 进行求前缀和操作，即可得到 $queryTime$ 时刻正在做作业的学生人数。

时间复杂度 $O(n+queryTime)$，空间复杂度 $O(1010)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def busyStudent(
        self, startTime: List[int], endTime: List[int], queryTime: int
    ) -> int:
        return sum(a <= queryTime <= b for a, b in zip(startTime, endTime))
```

```python
class Solution:
    def busyStudent(self, startTime: List[int], endTime: List[int], queryTime: int) -> int:
        c = [0] * 1010
        for a, b in zip(startTime, endTime):
            c[a] += 1
            c[b + 1] -= 1
        return sum(c[: queryTime + 1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ans = 0;
        for (int i = 0; i < startTime.length; ++i) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int[] c = new int[1010];
        for (int i = 0; i < startTime.length; ++i) {
            c[startTime[i]]++;
            c[endTime[i] + 1]--;
        }
        int ans = 0;
        for (int i = 0; i <= queryTime; ++i) {
            ans += c[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int busyStudent(vector<int>& startTime, vector<int>& endTime, int queryTime) {
        int ans = 0;
        for (int i = 0; i < startTime.size(); ++i) {
            ans += startTime[i] <= queryTime && queryTime <= endTime[i];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int busyStudent(vector<int>& startTime, vector<int>& endTime, int queryTime) {
        vector<int> c(1010);
        for (int i = 0; i < startTime.size(); ++i) {
            c[startTime[i]]++;
            c[endTime[i] + 1]--;
        }
        int ans = 0;
        for (int i = 0; i <= queryTime; ++i) {
            ans += c[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func busyStudent(startTime []int, endTime []int, queryTime int) int {
	ans := 0
	for i, a := range startTime {
		b := endTime[i]
		if a <= queryTime && queryTime <= b {
			ans++
		}
	}
	return ans
}
```

```go
func busyStudent(startTime []int, endTime []int, queryTime int) int {
	c := make([]int, 1010)
	for i, a := range startTime {
		b := endTime[i]
		c[a]++
		c[b+1]--
	}
	ans := 0
	for i := 0; i <= queryTime; i++ {
		ans += c[i]
	}
	return ans
}
```

### **C**

```c
int busyStudent(int* startTime, int startTimeSize, int* endTime, int endTimeSize, int queryTime) {
    int res = 0;
    for (int i = 0; i < startTimeSize; i++) {
        if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
            res++;
        }
    }
    return res;
}
```

### **TypeScript**

```ts
function busyStudent(
    startTime: number[],
    endTime: number[],
    queryTime: number,
): number {
    const n = startTime.length;
    let res = 0;
    for (let i = 0; i < n; i++) {
        if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
            res++;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn busy_student(start_time: Vec<i32>, end_time: Vec<i32>, query_time: i32) -> i32 {
        let mut res = 0;
        for i in 0..start_time.len() {
            if start_time[i] <= query_time && end_time[i] >= query_time {
                res += 1;
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
