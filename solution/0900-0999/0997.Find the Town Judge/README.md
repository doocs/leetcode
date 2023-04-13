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

**方法一：计数**

我们创建两个长度为 $n + 1$ 的数组 $cnt1$ 和 $cnt2$，分别表示每个人信任的人数和被信任的人数。

接下来，我们遍历数组 $trust$，对于每一项 $[a_i, b_i]$，我们将 $cnt1[a_i]$ 和 $cnt2[b_i]$ 分别加 $1$。

最后，我们在 $[1,..n]$ 范围内枚举每个人 $i$，如果 $cnt1[i] = 0$ 且 $cnt2[i] = n - 1$，则说明 $i$ 是小镇法官，返回 $i$ 即可。否则遍历结束后，返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $trust$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        cnt1 = [0] * (n + 1)
        cnt2 = [0] * (n + 1)
        for a, b in trust:
            cnt1[a] += 1
            cnt2[b] += 1
        for i in range(1, n + 1):
            if cnt1[i] == 0 and cnt2[i] == n - 1:
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] cnt1 = new int[n + 1];
        int[] cnt2 = new int[n + 1];
        for (var t : trust) {
            int a = t[0], b = t[1];
            ++cnt1[a];
            ++cnt2[b];
        }
        for (int i = 1; i <= n; ++i) {
            if (cnt1[i] == 0 && cnt2[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        vector<int> cnt1(n + 1);
        vector<int> cnt2(n + 1);
        for (auto& t : trust) {
            int a = t[0], b = t[1];
            ++cnt1[a];
            ++cnt2[b];
        }
        for (int i = 1; i <= n; ++i) {
            if (cnt1[i] == 0 && cnt2[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func findJudge(n int, trust [][]int) int {
	cnt1 := make([]int, n+1)
	cnt2 := make([]int, n+1)
	for _, t := range trust {
		a, b := t[0], t[1]
		cnt1[a]++
		cnt2[b]++
	}
	for i := 1; i <= n; i++ {
		if cnt1[i] == 0 && cnt2[i] == n-1 {
			return i
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function findJudge(n: number, trust: number[][]): number {
    const cnt1: number[] = new Array(n + 1).fill(0);
    const cnt2: number[] = new Array(n + 1).fill(0);
    for (const [a, b] of trust) {
        ++cnt1[a];
        ++cnt2[b];
    }
    for (let i = 1; i <= n; ++i) {
        if (cnt1[i] === 0 && cnt2[i] === n - 1) {
            return i;
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
