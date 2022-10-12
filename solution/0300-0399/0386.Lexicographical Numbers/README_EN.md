# [386. Lexicographical Numbers](https://leetcode.com/problems/lexicographical-numbers)

[中文文档](/solution/0300-0399/0386.Lexicographical%20Numbers/README.md)

## Description

<p>Given an integer <code>n</code>, return all the numbers in the range <code>[1, n]</code> sorted in lexicographical order.</p>

<p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time and uses <code>O(1)</code> extra space.&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 13
<strong>Output:</strong> [1,10,11,12,13,2,3,4,5,6,7,8,9]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 2
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        def dfs(u):
            if u > n:
                return
            ans.append(u)
            for i in range(10):
                dfs(u * 10 + i)

        ans = []
        for i in range(1, 10):
            dfs(i)
        return ans
```

```python
class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        v = 1
        ans = []
        for i in range(n):
            ans.append(v)
            if v * 10 <= n:
                v *= 10
            else:
                while v % 10 == 9 or v + 1 > n:
                    v //= 10
                v += 1
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, ans);
        }
        return ans;
    }

    private void dfs(int u, int n, List<Integer> ans) {
        if (u > n) {
            return;
        }
        ans.add(u);
        for (int i = 0; i < 10; ++i) {
            dfs(u * 10 + i, n, ans);
        }
    }
}
```

```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int v = 1;
        for (int i = 0; i < n; ++i) {
            ans.add(v);
            if (v * 10 <= n) {
                v *= 10;
            } else {
                while (v % 10 == 9 || v + 1 > n) {
                    v /= 10;
                }
                ++v;
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
    vector<int> lexicalOrder(int n) {
        vector<int> ans;
        for (int i = 1; i < 10; ++i) dfs(i, n, ans);
        return ans;
    }

    void dfs(int u, int n, vector<int>& ans) {
        if (u > n) return;
        ans.push_back(u);
        for (int i = 0; i < 10; ++i) dfs(u * 10 + i, n, ans);
    }
};
```

```cpp
class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> ans;
        int v = 1;
        for (int i = 0; i < n; ++i) {
            ans.push_back(v);
            if (v * 10 <= n)
                v *= 10;
            else {
                while (v % 10 == 9 || v + 1 > n) v /= 10;
                ++v;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func lexicalOrder(n int) []int {
	var ans []int
	var dfs func(u int)
	dfs = func(u int) {
		if u > n {
			return
		}
		ans = append(ans, u)
		for i := 0; i < 10; i++ {
			dfs(u*10 + i)
		}
	}
	for i := 1; i < 10; i++ {
		dfs(i)
	}
	return ans
}
```

```go
func lexicalOrder(n int) []int {
	var ans []int
	v := 1
	for i := 0; i < n; i++ {
		ans = append(ans, v)
		if v*10 <= n {
			v *= 10
		} else {
			for v%10 == 9 || v+1 > n {
				v /= 10
			}
			v++
		}
	}
	return ans
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(mut num: i32, n: i32, res: &mut Vec<i32>) {
        if num > n {
            return;
        }
        res.push(num);
        for i in 0..10 {
            Self::dfs(num * 10 + i, n, res);
        }
    }

    pub fn lexical_order(n: i32) -> Vec<i32> {
        let mut res = vec![];
        for i in 1..10 {
            Self::dfs(i, n, &mut res);
        }
        res
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var lexicalOrder = function (n) {
    let ans = [];
    function dfs(u) {
        if (u > n) {
            return;
        }
        ans.push(u);
        for (let i = 0; i < 10; ++i) {
            dfs(u * 10 + i);
        }
    }
    for (let i = 1; i < 10; ++i) {
        dfs(i);
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
