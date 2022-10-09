# [2432. 处理用时最长的那个任务的员工](https://leetcode.cn/problems/the-employee-that-worked-on-the-longest-task)

[English Version](/solution/2400-2499/2432.The%20Employee%20That%20Worked%20on%20the%20Longest%20Task/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>共有 <code>n</code> 位员工，每位员工都有一个从 <code>0</code> 到 <code>n - 1</code> 的唯一 id 。</p>

<p>给你一个二维整数数组 <code>logs</code> ，其中 <code>logs[i] = [id<sub>i</sub>, leaveTime<sub>i</sub>]</code> ：</p>

<ul>
	<li><code>id<sub>i</sub></code> 是处理第 <code>i</code> 个任务的员工的 id ，且</li>
	<li><code>leaveTime<sub>i</sub></code> 是员工完成第 <code>i</code> 个任务的时刻。所有 <code>leaveTime<sub>i</sub></code> 的值都是 <strong>唯一</strong> 的。</li>
</ul>

<p>注意，第 <code>i</code> 个任务在第 <code>(i - 1)</code> 个任务结束后立即开始，且第 <code>0</code> 个任务从时刻 <code>0</code> 开始。</p>

<p>返回处理用时最长的那个任务的员工的 id 。如果存在两个或多个员工同时满足，则返回几人中 <strong>最小</strong> 的 id 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10, logs = [[0,3],[2,5],[0,9],[1,15]]
<strong>输出：</strong>1
<strong>解释：</strong>
任务 0 于时刻 0 开始，且在时刻 3 结束，共计 3 个单位时间。
任务 1 于时刻 3 开始，且在时刻 5 结束，共计 2 个单位时间。
任务 2 于时刻 5 开始，且在时刻 9 结束，共计 4 个单位时间。
任务 3 于时刻 9 开始，且在时刻 15 结束，共计 6 个单位时间。
时间最长的任务是任务 3 ，而 id 为 1 的员工是处理此任务的员工，所以返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 26, logs = [[1,1],[3,7],[2,12],[7,17]]
<strong>输出：</strong>3
<strong>解释：</strong>
任务 0 于时刻 0 开始，且在时刻 1 结束，共计 1 个单位时间。
任务 1 于时刻 1 开始，且在时刻 7 结束，共计 6 个单位时间。
任务 2 于时刻 7 开始，且在时刻 12 结束，共计 5 个单位时间。
任务 3 于时刻 12 开始，且在时刻 17 结束，共计 5 个单位时间。
时间最长的任务是任务 1 ，而 id 为 3 的员工是处理此任务的员工，所以返回 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, logs = [[0,10],[1,20]]
<strong>输出：</strong>0
<strong>解释：</strong>
任务 0 于时刻 0 开始，且在时刻 10 结束，共计 10 个单位时间。
任务 1 于时刻 10 开始，且在时刻 20 结束，共计 10 个单位时间。
时间最长的任务是任务 0 和 1 ，处理这两个任务的员工的 id 分别是 0 和 1 ，所以返回最小的 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= logs.length &lt;= 500</code></li>
	<li><code>logs[i].length == 2</code></li>
	<li><code>0 &lt;= id<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= leaveTime<sub>i</sub> &lt;= 500</code></li>
	<li><code>id<sub>i</sub> != id<sub>i + 1</sub></code></li>
	<li><code>leaveTime<sub>i</sub></code> 按严格递增顺序排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

遍历 `logs`，记录每个员工的工作时间，最后返回工作时间最长且 id 最小的员工。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为 `logs` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hardestWorker(self, n: int, logs: List[List[int]]) -> int:
        ans = mx = last = 0
        for uid, t in logs:
            x = t - last
            if mx < x:
                mx = x
                ans = uid
            elif mx == x and ans > uid:
                ans = uid
            last = t
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int ans = 0, mx = 0, last = 0;
        for (var e : logs) {
            int uid = e[0], t = e[1];
            int x = t - last;
            if (mx < x) {
                mx = x;
                ans = uid;
            } else if (mx == x && ans > uid) {
                ans = uid;
            }
            last = t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int hardestWorker(int n, vector<vector<int>>& logs) {
        int ans = 0, mx = 0, last = 0;
        for (auto& e : logs) {
            int uid = e[0], t = e[1];
            int x = t - last;
            if (mx < x) {
                mx = x;
                ans = uid;
            } else if (mx == x && ans > uid) {
                ans = uid;
            }
            last = t;
        }
        return ans;
    }
};
```

### **Go**

```go
func hardestWorker(n int, logs [][]int) int {
	ans, mx, last := 0, 0, 0
	for _, e := range logs {
		uid, t := e[0], e[1]
		x := t - last
		if mx < x {
			mx, ans = x, uid
		} else if mx == x && ans > uid {
			ans = uid
		}
		last = t
	}
	return ans
}
```

### **TypeScript**

```ts
function hardestWorker(n: number, logs: number[][]): number {
    let [ans, max_num] = logs[0];
    for (let i = 1; i < logs.length; i++) {
        let duration = logs[i][1] - logs[i - 1][1];
        let id = logs[i][0];
        if (duration > max_num || (duration == max_num && id < ans)) {
            ans = id;
            max_num = duration;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
