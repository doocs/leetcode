# [997. 找到小镇的法官](https://leetcode.cn/problems/find-the-town-judge)

[English Version](/solution/0900-0999/0997.Find%20the%20Town%20Judge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>小镇里有 <code>n</code> 个人，按从 <code>1</code> 到 <code>n</code> 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。</p>

<p>如果小镇法官真的存在，那么：</p>

<ol>
	<li>小镇法官不会信任任何人。</li>
	<li>每个人（除了小镇法官）都信任这位小镇法官。</li>
	<li>只有一个人同时满足属性 <strong>1</strong> 和属性 <strong>2</strong> 。</li>
</ol>

<p>给你一个数组 <code>trust</code> ，其中 <code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示编号为 <code>a<sub>i</sub></code> 的人信任编号为 <code>b<sub>i</sub></code> 的人。</p>

<p>如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2, trust = [[1,2]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, trust = [[1,3],[2,3]]
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3, trust = [[1,3],[2,3],[3,1]]
<strong>输出：</strong>-1
</pre>

&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= trust.length &lt;= 10<sup>4</sup></code></li>
	<li><code>trust[i].length == 2</code></li>
	<li><code>trust</code> 中的所有<code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> <strong>互不相同</strong></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

创建长度为 1001 的计数器 `c1[i]`、`c2[i]`，其中 `c1[i]` 表示 i 信任的人数，`c2[i]` 表示信任 i 的人数。

遍历 trust 列表，统计人数，最后再遍历计数器，若存在 `c1[i] == 0 && c2[i] == n - 1`，说明存在法官，直接返回 i。否则遍历结束返回 -1。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        N = 1001
        c1, c2 = [0] * N, [0] * N
        for a, b in trust:
            c1[a] += 1
            c2[b] += 1
        for i in range(1, N):
            if c1[i] == 0 and c2[i] == n - 1:
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findJudge(int n, int[][] trust) {
        int N = 1001;
        int[] c1 = new int[N];
        int[] c2 = new int[N];
        for (int[] e : trust) {
            ++c1[e[0]];
            ++c2[e[1]];
        }
        for (int i = 1; i < N; ++i) {
            if (c1[i] == 0 && c2[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
```

### **TypeScript**

```ts
function findJudge(n: number, trust: number[][]): number {
    let candidates = new Array(n).fill(0);
    for (let [a, b] of trust) {
        candidates[a - 1] = -1;
        if (candidates[b - 1] >= 0) {
            candidates[b - 1]++;
        }
    }

    for (let i = 0; i < n; i++) {
        if (candidates[i] == n - 1) {
            return i + 1;
        }
    }
    return -1;
}
```

### **C++**

```cpp
class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        int N = 1001;
        vector<int> c1(N);
        vector<int> c2(N);
        for (auto& e : trust) {
            ++c1[e[0]];
            ++c2[e[1]];
        }
        for (int i = 1; i < N; ++i) {
            if (c1[i] == 0 && c2[i] == n - 1) return i;
        }
        return -1;
    }
};
```

### **Go**

```go
func findJudge(n int, trust [][]int) int {
	N := 1001
	c1 := make([]int, N)
	c2 := make([]int, N)
	for _, e := range trust {
		c1[e[0]]++
		c2[e[1]]++
	}
	for i := 1; i < N; i++ {
		if c1[i] == 0 && c2[i] == n-1 {
			return i
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
