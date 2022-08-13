# [821. 字符的最短距离](https://leetcode.cn/problems/shortest-distance-to-a-character)

[English Version](/solution/0800-0899/0821.Shortest%20Distance%20to%20a%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个字符 <code>c</code> ，且 <code>c</code> 是 <code>s</code> 中出现过的字符。</p>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer.length == s.length</code> 且 <code>answer[i]</code> 是 <code>s</code> 中从下标 <code>i</code> 到离它 <strong>最近</strong> 的字符 <code>c</code> 的 <strong>距离</strong> 。</p>

<p>两个下标&nbsp;<code>i</code> 和 <code>j</code> 之间的 <strong>距离</strong> 为 <code>abs(i - j)</code> ，其中 <code>abs</code> 是绝对值函数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "loveleetcode", c = "e"
<strong>输出：</strong>[3,2,1,0,1,0,0,1,2,2,1,0]
<strong>解释：</strong>字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaab", c = "b"
<strong>输出：</strong>[3,2,1,0]
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s[i]</code> 和 <code>c</code> 均为小写英文字母</li>
	<li>题目数据保证 <code>c</code> 在 <code>s</code> 中至少出现一次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次遍历**

两次遍历，找出每个字符左侧和右侧最近的 c，算出最短距离。

**方法二：BFS**

在字符串 s 中找出所有字符 c 对应的下标，并放入队列 q。

BFS 向左右两边扩散，找出最短距离。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        n = len(s)
        ans = [0] * n
        j = inf
        for i, ch in enumerate(s):
            if ch == c:
                j = i
            ans[i] = abs(i - j)
        j = inf
        for i in range(n - 1, -1, -1):
            if s[i] == c:
                j = i
            ans[i] = min(ans[i], abs(i - j))
        return ans
```

```python
class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        q = deque([i for i, ch in enumerate(s) if ch == c])
        ans = [0 if ch == c else -1 for ch in s]
        d = 0
        while q:
            d += 1
            for _ in range(len(q)):
                i = q.popleft()
                for j in (i - 1, i + 1):
                    if 0 <= j < len(s) and ans[j] == -1:
                        ans[j] = d
                        q.append(j)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        for (int i = 0, j = Integer.MAX_VALUE; i < n; ++i) {
            if (s.charAt(i) == c) {
                j = i;
            }
            ans[i] = Math.abs(i - j);
        }
        for (int i = n - 1, j = Integer.MAX_VALUE; i >= 0; --i) {
            if (s.charAt(i) == c) {
                j = i;
            }
            ans[i] = Math.min(ans[i], Math.abs(i - j));
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] shortestToChar(String s, char c) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = s.length();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == c) {
                q.offer(i);
                ans[i] = 0;
            }
        }
        int d = 0;
        while (!q.isEmpty()) {
            ++d;
            for (int t = q.size(); t > 0; --t) {
                int i = q.poll();
                for (int j : Arrays.asList(i - 1, i + 1)) {
                    if (j >= 0 && j < n && ans[j] == -1) {
                        ans[j] = d;
                        q.offer(j);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    let ans = [];
    let pre = Infinity;
    for (let i = 0; i < n; i++) {
        if (s.charAt(i) == c) pre = i;
        ans[i] = Math.abs(pre - i);
    }
    pre = Infinity;
    for (let i = n - 1; i > -1; i--) {
        if (s.charAt(i) == c) pre = i;
        ans[i] = Math.min(Math.abs(pre - i), ans[i]);
    }
    return ans;
}
```

```ts
function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    const idxs = [];
    for (let i = 0; i < n; i++) {
        if (s[i] === c) {
            idxs.push(i);
        }
    }
    idxs.push(Infinity);

    const res = new Array(n);
    let i = 0;
    for (let j = 0; j < n; j++) {
        if (Math.abs(idxs[i] - j) > Math.abs(idxs[i + 1] - j)) {
            i++;
        }
        res[j] = Math.abs(idxs[i] - j);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn shortest_to_char(s: String, c: char) -> Vec<i32> {
        let c = c as u8;
        let s = s.as_bytes();
        let n = s.len();
        let mut res = vec![i32::MAX; n];
        let mut pre = i32::MAX;
        for i in 0..n {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = i32::abs(i as i32 - pre);
        }
        pre = i32::MAX;
        for i in (0..n).rev() {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = res[i].min(i32::abs(i as i32 - pre));
        }
        res
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> shortestToChar(string s, char c) {
        int n = s.size();
        vector<int> ans(n);
        for (int i = 0, j = INT_MAX; i < n; ++i) {
            if (s[i] == c) j = i;
            ans[i] = abs(i - j);
        }
        for (int i = n - 1, j = INT_MAX; i >= 0; --i) {
            if (s[i] == c) j = i;
            ans[i] = min(ans[i], abs(i - j));
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> shortestToChar(string s, char c) {
        int n = s.size();
        vector<int> ans(n, -1);
        queue<int> q;
        for (int i = 0; i < n; ++i)
        {
            if (s[i] == c)
            {
                q.push(i);
                ans[i] = 0;
            }
        }
        int d = 0;
        while (!q.empty())
        {
            ++d;
            for (int t = q.size(); t > 0; --t)
            {
                int i = q.front();
                q.pop();
                vector<int> dirs{i - 1, i + 1};
                for (int& j : dirs)
                {
                    if (j >= 0 && j < n && ans[j] == -1)
                    {
                        ans[j] = d;
                        q.push(j);
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestToChar(s string, c byte) []int {
	n := len(s)
	ans := make([]int, n)
	for i, j := 0, -10000; i < n; i++ {
		if s[i] == c {
			j = i
		}
		ans[i] = i - j
	}
	for i, j := n-1, 10000; i >= 0; i-- {
		if s[i] == c {
			j = i
		}
		if j-i < ans[i] {
			ans[i] = j - i
		}
	}
	return ans
}
```

```go
func shortestToChar(s string, c byte) []int {
	n := len(s)
	var q []int
	ans := make([]int, n)
	for i := range s {
		ans[i] = -1
		if s[i] == c {
			q = append(q, i)
			ans[i] = 0
		}
	}

	d := 0
	for len(q) > 0 {
		d++
		for t := len(q); t > 0; t-- {
			i := q[0]
			q = q[1:]
			for _, j := range []int{i - 1, i + 1} {
				if j >= 0 && j < n && ans[j] == -1 {
					ans[j] = d
					q = append(q, j)
				}
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
