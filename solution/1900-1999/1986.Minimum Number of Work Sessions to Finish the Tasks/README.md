# [1986. 完成任务的最少工作时间段](https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks)

[English Version](/solution/1900-1999/1986.Minimum%20Number%20of%20Work%20Sessions%20to%20Finish%20the%20Tasks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你被安排了 <code>n</code>&nbsp;个任务。任务需要花费的时间用长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>tasks</code>&nbsp;表示，第 <code>i</code>&nbsp;个任务需要花费&nbsp;<code>tasks[i]</code>&nbsp;小时完成。一个 <strong>工作时间段</strong>&nbsp;中，你可以 <strong>至多</strong>&nbsp;连续工作&nbsp;<code>sessionTime</code>&nbsp;个小时，然后休息一会儿。</p>

<p>你需要按照如下条件完成给定任务：</p>

<ul>
	<li>如果你在某一个时间段开始一个任务，你需要在 <strong>同一个</strong>&nbsp;时间段完成它。</li>
	<li>完成一个任务后，你可以 <strong>立马</strong>&nbsp;开始一个新的任务。</li>
	<li>你可以按 <strong>任意顺序</strong>&nbsp;完成任务。</li>
</ul>

<p>给你&nbsp;<code>tasks</code> 和&nbsp;<code>sessionTime</code>&nbsp;，请你按照上述要求，返回完成所有任务所需要的&nbsp;<strong>最少</strong>&nbsp;数目的&nbsp;<strong>工作时间段</strong>&nbsp;。</p>

<p>测试数据保证&nbsp;<code>sessionTime</code> <strong>大于等于</strong>&nbsp;<code>tasks[i]</code>&nbsp;中的&nbsp;<strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [1,2,3], sessionTime = 3
<b>输出：</b>2
<b>解释：</b>你可以在两个工作时间段内完成所有任务。
- 第一个工作时间段：完成第一和第二个任务，花费 1 + 2 = 3 小时。
- 第二个工作时间段：完成第三个任务，花费 3 小时。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [3,1,3,1,1], sessionTime = 8
<b>输出：</b>2
<b>解释：</b>你可以在两个工作时间段内完成所有任务。
- 第一个工作时间段：完成除了最后一个任务以外的所有任务，花费 3 + 1 + 3 + 1 = 8 小时。
- 第二个工作时间段，完成最后一个任务，花费 1 小时。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>tasks = [1,2,3,4,5], sessionTime = 15
<b>输出：</b>1
<b>解释：</b>你可以在一个工作时间段以内完成所有任务。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == tasks.length</code></li>
	<li><code>1 &lt;= n &lt;= 14</code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10</code></li>
	<li><code>max(tasks[i]) &lt;= sessionTime &lt;= 15</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩动态规划 + 枚举子集**

我们注意到 $n$ 不超过 $14$，因此，我们可以考虑使用状态压缩动态规划的方法求解本题。

我们用一个长度为 $n$ 的二进制数 $i$ 来表示当前的任务状态，其中 $i$ 的第 $j$ 位为 $1$，当且仅当第 $j$ 个任务被完成。我们用 $f[i]$ 表示完成状态为 $i$ 的所有任务所需要的最少工作时间段数。

我们可以枚举 $i$ 的所有子集 $j$，其中 $j$ 的二进制表示中的每一位都是 $i$ 的二进制表示中对应位的子集，即 $j \subseteq i$。如果 $j$ 对应的任务可以在一个工作时间段内完成，那么我们可以用 $f[i \oplus j] + 1$ 来更新 $f[i]$，其中 $i \oplus j$ 表示 $i$ 和 $j$ 的按位异或。

最终的答案即为 $f[2^n - 1]$。

时间复杂度 $O(n \times 3^n)$，空间复杂度 $O(2^n)$。其中 $n$ 为任务的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSessions(self, tasks: List[int], sessionTime: int) -> int:
        n = len(tasks)
        ok = [False] * (1 << n)
        for i in range(1, 1 << n):
            t = sum(tasks[j] for j in range(n) if i >> j & 1)
            ok[i] = t <= sessionTime
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1, 1 << n):
            j = i
            while j:
                if ok[j]:
                    f[i] = min(f[i], f[i ^ j] + 1)
                j = (j - 1) & i
        return f[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        boolean[] ok = new boolean[1 << n];
        for (int i = 1; i < 1 << n; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    t += tasks[j];
                }
            }
            ok[i] = t <= sessionTime;
        }
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            for (int j = i; j > 0; j = (j - 1) & i) {
                if (ok[j]) {
                    f[i] = Math.min(f[i], f[i ^ j] + 1);
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSessions(vector<int>& tasks, int sessionTime) {
        int n = tasks.size();
        bool ok[1 << n];
        memset(ok, false, sizeof(ok));
        for (int i = 1; i < 1 << n; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    t += tasks[j];
                }
            }
            ok[i] = t <= sessionTime;
        }
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            for (int j = i; j; j = (j - 1) & i) {
                if (ok[j]) {
                    f[i] = min(f[i], f[i ^ j] + 1);
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

### **Go**

```go
func minSessions(tasks []int, sessionTime int) int {
	n := len(tasks)
	ok := make([]bool, 1<<n)
	f := make([]int, 1<<n)
	for i := 1; i < 1<<n; i++ {
		t := 0
		f[i] = 1 << 30
		for j, x := range tasks {
			if i>>j&1 == 1 {
				t += x
			}
		}
		ok[i] = t <= sessionTime
	}
	for i := 1; i < 1<<n; i++ {
		for j := i; j > 0; j = (j - 1) & i {
			if ok[j] {
				f[i] = min(f[i], f[i^j]+1)
			}
		}
	}
	return f[1<<n-1]
}
```

### **TypeScript**

```ts
function minSessions(tasks: number[], sessionTime: number): number {
    const n = tasks.length;
    const ok: boolean[] = new Array(1 << n).fill(false);
    for (let i = 1; i < 1 << n; ++i) {
        let t = 0;
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                t += tasks[j];
            }
        }
        ok[i] = t <= sessionTime;
    }

    const f: number[] = new Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 1; i < 1 << n; ++i) {
        for (let j = i; j > 0; j = (j - 1) & i) {
            if (ok[j]) {
                f[i] = Math.min(f[i], f[i ^ j] + 1);
            }
        }
    }
    return f[(1 << n) - 1];
}
```

### **...**

```

```

<!-- tabs:end -->
