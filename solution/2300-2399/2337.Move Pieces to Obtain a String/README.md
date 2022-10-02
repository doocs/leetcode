# [2337. 移动片段得到字符串](https://leetcode.cn/problems/move-pieces-to-obtain-a-string)

[English Version](/solution/2300-2399/2337.Move%20Pieces%20to%20Obtain%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>start</code> 和 <code>target</code> ，长度均为 <code>n</code> 。每个字符串 <strong>仅</strong> 由字符 <code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成，其中：</p>

<ul>
	<li>字符 <code>'L'</code> 和 <code>'R'</code> 表示片段，其中片段 <code>'L'</code> 只有在其左侧直接存在一个 <strong>空位</strong> 时才能向 <strong>左</strong> 移动，而片段 <code>'R'</code> 只有在其右侧直接存在一个 <strong>空位</strong> 时才能向 <strong>右</strong> 移动。</li>
	<li>字符 <code>'_'</code> 表示可以被 <strong>任意</strong> <code>'L'</code> 或 <code>'R'</code> 片段占据的空位。</li>
</ul>

<p>如果在移动字符串 <code>start</code> 中的片段任意次之后可以得到字符串 <code>target</code> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>start = "_L__R__R_", target = "L______RR"
<strong>输出：</strong>true
<strong>解释：</strong>可以从字符串 start 获得 target ，需要进行下面的移动：
- 将第一个片段向左移动一步，字符串现在变为 "<strong>L</strong>___R__R_" 。
- 将最后一个片段向右移动一步，字符串现在变为 "L___R___<strong>R</strong>" 。
- 将第二个片段向右移动散步，字符串现在变为 "L______<strong>R</strong>R" 。
可以从字符串 start 得到 target ，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>start = "R_L_", target = "__LR"
<strong>输出：</strong>false
<strong>解释：</strong>字符串 start 中的 'R' 片段可以向右移动一步得到 "_<strong>R</strong>L_" 。
但是，在这一步之后，不存在可以移动的片段，所以无法从字符串 start 得到 target 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>start = "_R", target = "R_"
<strong>输出：</strong>false
<strong>解释：</strong>字符串 start 中的片段只能向右移动，所以无法从字符串 start 得到 target 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == start.length == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>start</code> 和 <code>target</code> 由字符 <code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

替换操作实际上是将 `L` 往左移动（`L` 左边为 `_` 时才能移动），`R` 往右移动（`R` 右边是 `_` 时才能移动），但 `L` 无法穿过 `R`。所以，如果去掉 `start` 和 `target` 中的所有 `_`，剩下的字符应该是相同的，否则返回 `false`。

双指针遍历 `start` 和 `target`：

-   如果当前字符为 `L` 且 $i\lt j$，那么这个 `L` 无法向右移动，返回 `false`；
-   如果当前字符为 `R` 且 $i\gt j$，那么这个 `R` 无法向左移动，返回 `false`。

如果双指针均遍历到末尾，返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 表示字符串 `start` 或 `target` 的长度。

相似题目：[777. 在 LR 字符串中交换相邻字符](/solution/0700-0799/0777.Swap%20Adjacent%20in%20LR%20String/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canChange(self, start: str, target: str) -> bool:
        a = [(v, i) for i, v in enumerate(start) if v != '_']
        b = [(v, i) for i, v in enumerate(target) if v != '_']
        if len(a) != len(b):
            return False
        for (c, i), (d, j) in zip(a, b):
            if c != d:
                return False
            if c == 'L' and i < j:
                return False
            if c == 'R' and i > j:
                return False
        return True
```

```python
class Solution:
    def canChange(self, start: str, target: str) -> bool:
        n = len(start)
        i = j = 0
        while 1:
            while i < n and start[i] == '_':
                i += 1
            while j < n and target[j] == '_':
                j += 1
            if i >= n and j >= n:
                return True
            if i >= n or j >= n or start[i] != target[j]:
                return False
            if start[i] == 'L' and i < j:
                return False
            if start[i] == 'R' and i > j:
                return False
            i, j = i + 1, j + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canChange(String start, String target) {
        List<int[]> a = f(start);
        List<int[]> b = f(target);
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            int[] x = a.get(i);
            int[] y = b.get(i);
            if (x[0] != y[0]) {
                return false;
            }
            if (x[0] == 1 && x[1] < y[1]) {
                return false;
            }
            if (x[0] == 2 && x[1] > y[1]) {
                return false;
            }
        }
        return true;
    }

    private List<int[]> f(String s) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'L') {
                res.add(new int[] {1, i});
            } else if (s.charAt(i) == 'R') {
                res.add(new int[] {2, i});
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (true) {
            while (i < n && start.charAt(i) == '_') {
                ++i;
            }
            while (j < n && target.charAt(j) == '_') {
                ++j;
            }
            if (i == n && j == n) {
                return true;
            }
            if (i == n || j == n || start.charAt(i) != target.charAt(j)) {
                return false;
            }
            if (start.charAt(i) == 'L' && i < j || start.charAt(i) == 'R' && i > j) {
                return false;
            }
            ++i;
            ++j;
        }
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    bool canChange(string start, string target) {
        auto a = f(start);
        auto b = f(target);
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); ++i) {
            auto x = a[i], y = b[i];
            if (x.first != y.first) return false;
            if (x.first == 1 && x.second < y.second) return false;
            if (x.first == 2 && x.second > y.second) return false;
        }
        return true;
    }

    vector<pair<int, int>> f(string s) {
        vector<pii> res;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == 'L')
                res.push_back({1, i});
            else if (s[i] == 'R')
                res.push_back({2, i});
        }
        return res;
    }
};
```

```cpp
class Solution {
public:
    bool canChange(string start, string target) {
        int n = start.size();
        int i = 0, j = 0;
        while (true) {
            while (i < n && start[i] == '_') ++i;
            while (j < n && target[j] == '_') ++j;
            if (i == n && j == n) return true;
            if (i == n || j == n || start[i] != target[j]) return false;
            if (start[i] == 'L' && i < j) return false;
            if (start[i] == 'R' && i > j) return false;
            ++i;
            ++j;
        }
    }
};
```

### **Go**

```go
func canChange(start string, target string) bool {
	f := func(s string) [][]int {
		res := [][]int{}
		for i, c := range s {
			if c == 'L' {
				res = append(res, []int{1, i})
			} else if c == 'R' {
				res = append(res, []int{2, i})
			}
		}
		return res
	}

	a, b := f(start), f(target)
	if len(a) != len(b) {
		return false
	}
	for i, x := range a {
		y := b[i]
		if x[0] != y[0] {
			return false
		}
		if x[0] == 1 && x[1] < y[1] {
			return false
		}
		if x[0] == 2 && x[1] > y[1] {
			return false
		}
	}
	return true
}
```

```go
func canChange(start string, target string) bool {
	n := len(start)
	i, j := 0, 0
	for {
		for i < n && start[i] == '_' {
			i++
		}
		for j < n && target[j] == '_' {
			j++
		}
		if i == n && j == n {
			return true
		}
		if i == n || j == n || start[i] != target[j] {
			return false
		}
		if start[i] == 'L' && i < j {
			return false
		}
		if start[i] == 'R' && i > j {
			return false
		}
		i, j = i+1, j+1
	}
}
```

### **TypeScript**

```ts
function canChange(start: string, target: string): boolean {
    if (
        [...start].filter(c => c !== '_').join('') !==
        [...target].filter(c => c !== '_').join('')
    ) {
        return false;
    }
    const n = start.length;
    let i = 0;
    let j = 0;
    while (i < n || j < n) {
        while (start[i] === '_') {
            i++;
        }
        while (target[j] === '_') {
            j++;
        }
        if (start[i] === 'R') {
            if (i > j) {
                return false;
            }
        }
        if (start[i] === 'L') {
            if (i < j) {
                return false;
            }
        }
        i++;
        j++;
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
