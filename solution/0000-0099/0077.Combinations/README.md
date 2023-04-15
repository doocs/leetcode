# [77. 组合](https://leetcode.cn/problems/combinations)

[English Version](/solution/0000-0099/0077.Combinations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数 <code>n</code> 和 <code>k</code>，返回范围 <code>[1, n]</code> 中所有可能的 <code>k</code> 个数的组合。</p>

<p>你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 4, k = 2
<strong>输出：</strong>
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 1
<strong>输出：</strong>[[1]]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 20</code></li>
	<li><code>1 <= k <= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：回溯（两种方式）**

我们设计一个函数 $dfs(i)$，表示从数字 $i$ 开始搜索，当前搜索路径为 $t$，答案为 $ans$。

函数 $dfs(i)$ 的执行逻辑如下：

-   如果当前搜索路径 $t$ 的长度等于 $k$，则将当前搜索路径加入答案，然后返回。
-   如果 $i \gt n$，则说明搜索已经结束，返回。
-   否则，我们可以选择将数字 $i$ 加入搜索路径 $t$，然后继续搜索，即执行 $dfs(i + 1)$，然后将数字 $i$ 从搜索路径 $t$ 中移除；或者不将数字 $i$ 加入搜索路径 $t$，直接执行 $dfs(i + 1)$。

以上做法实际上是枚举当前数字选或者不选，然后递归地搜索下一个数字。我们也可以枚举下一个要选择的数字 $j$，其中 $i \leq j \leq n$，如果下一个要选择的数字是 $j$，那么我们将数字 $j$ 加入搜索路径 $t$，然后继续搜索，即执行 $dfs(j + 1)$，接着将数字 $j$ 从搜索路径 $t$ 中移除。

在主函数中，我们从数字 $1$ 开始搜索，即执行 $dfs(1)$。

时间复杂度 $(C_n^k \times k)$，空间复杂度 $O(k)$。其中 $C_n^k$ 表示组合数。

相似题目：

-   [39. 组合总和](/solution/0000-0099/0039.Combination%20Sum/README.md)
-   [40. 组合总和 II](/solution/0000-0099/0040.Combination%20Sum%20II/README.md)
-   [216. 组合总和 III](/solution/0200-0299/0216.Combination%20Sum%20III/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        def dfs(i: int):
            if len(t) == k:
                ans.append(t[:])
                return
            if i > n:
                return
            t.append(i)
            dfs(i + 1)
            t.pop()
            dfs(i + 1)

        ans = []
        t = []
        dfs(1)
        return ans
```

```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        def dfs(i: int):
            if len(t) == k:
                ans.append(t[:])
                return
            if i > n:
                return
            for j in range(i, n + 1):
                t.append(j)
                dfs(j + 1)
                t.pop()

        ans = []
        t = []
        dfs(1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.add(i);
        dfs(i + 1);
        t.remove(t.size() - 1);
        dfs(i + 1);
    }
}
```

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.add(j);
            dfs(j + 1);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) {
            if (t.size() == k) {
                ans.emplace_back(t);
                return;
            }
            if (i > n) {
                return;
            }
            t.emplace_back(i);
            dfs(i + 1);
            t.pop_back();
            dfs(i + 1);
        };
        dfs(1);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) {
            if (t.size() == k) {
                ans.emplace_back(t);
                return;
            }
            if (i > n) {
                return;
            }
            for (int j = i; j <= n; ++j) {
                t.emplace_back(j);
                dfs(j + 1);
                t.pop_back();
            }
        };
        dfs(1);
        return ans;
    }
};
```

### **Go**

```go
func combine(n int, k int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == k {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		if i > n {
			return
		}
		t = append(t, i)
		dfs(i + 1)
		t = t[:len(t)-1]
		dfs(i + 1)
	}
	dfs(1)
	return
}
```

```go
func combine(n int, k int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == k {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		if i > n {
			return
		}
		for j := i; j <= n; j++ {
			t = append(t, j)
			dfs(j + 1)
			t = t[:len(t)-1]
		}
	}
	dfs(1)
	return
}
```

### **TypeScript**

```ts
function combine(n: number, k: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (t.length === k) {
            ans.push(t.slice());
            return;
        }
        if (i > n) {
            return;
        }
        t.push(i);
        dfs(i + 1);
        t.pop();
        dfs(i + 1);
    };
    dfs(1);
    return ans;
}
```

```ts
function combine(n: number, k: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (t.length === k) {
            ans.push(t.slice());
            return;
        }
        if (i > n) {
            return;
        }
        for (let j = i; j <= n; ++j) {
            t.push(j);
            dfs(j + 1);
            t.pop();
        }
    };
    dfs(1);
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if t.len() == k as usize {
            ans.push(t.clone());
            return;
        }
        if i > n {
            return;
        }
        t.push(i);
        Self::dfs(i + 1, n, k, t, ans);
        t.pop();
        Self::dfs(i + 1, n, k, t, ans);
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut ans);
        ans
    }
}
```

```rust
impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if t.len() == k as usize {
            ans.push(t.clone());
            return;
        }
        if i > n {
            return;
        }
        for j in i..=n {
            t.push(j);
            Self::dfs(j + 1, n, k, t, ans);
            t.pop();
        }
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut ans);
        ans
    }
}
```

### **C#**

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int n;
    private int k;

    public IList<IList<int>> Combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.Count == k) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.Add(i);
        dfs(i + 1);
        t.RemoveAt(t.Count - 1);
        dfs(i + 1);
    }
}
```

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int n;
    private int k;

    public IList<IList<int>> Combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.Count == k) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.Add(j);
            dfs(j + 1);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
