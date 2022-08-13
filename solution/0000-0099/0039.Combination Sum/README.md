# [39. 组合总和](https://leetcode.cn/problems/combination-sum)

[English Version](/solution/0000-0099/0039.Combination%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>无重复元素</strong> 的整数数组&nbsp;<code>candidates</code> 和一个目标整数&nbsp;<code>target</code>&nbsp;，找出&nbsp;<code>candidates</code>&nbsp;中可以使数字和为目标数&nbsp;<code>target</code> 的 <em>所有&nbsp;</em><strong>不同组合</strong> ，并以列表形式返回。你可以按 <strong>任意顺序</strong> 返回这些组合。</p>

<p><code>candidates</code> 中的 <strong>同一个</strong> 数字可以 <strong>无限制重复被选取</strong> 。如果至少一个数字的被选数量不同，则两种组合是不同的。&nbsp;</p>

<p>对于给定的输入，保证和为&nbsp;<code>target</code> 的不同组合数少于 <code>150</code> 个。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>candidates = <code>[2,3,6,7], </code>target = <code>7</code>
<strong>输出：</strong>[[2,2,3],[7]]
<strong>解释：</strong>
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入: </strong>candidates = [2,3,5]<code>, </code>target = 8
<strong>输出: </strong>[[2,2,2,2],[2,3,3],[3,5]]</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入: </strong>candidates = <code>[2], </code>target = 1
<strong>输出: </strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 30</code></li>
	<li><code>1 &lt;= candidates[i] &lt;= 200</code></li>
	<li><code>candidate</code> 中的每个元素都 <strong>互不相同</strong></li>
	<li><code>1 &lt;= target &lt;= 500</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

为了避免重复方案，需要定义一个搜索起点。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(s, u, t):
            if s == target:
                ans.append(t[:])
                return
            if s > target:
                return
            for i in range(u, len(candidates)):
                c = candidates[i]
                t.append(c)
                dfs(s + c, i, t)
                t.pop()

        ans = []
        dfs(0, 0, [])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans;
    private int target;
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        this.target = target;
        this.candidates = candidates;
        dfs(0, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int s, int u, List<Integer> t) {
        if (s == target) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (s > target) {
            return;
        }
        for (int i = u; i < candidates.length; ++i) {
            int c = candidates[i];
            t.add(c);
            dfs(s + c, i, t);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> ans;
    vector<int> candidates;
    int target;

    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        this->candidates = candidates;
        this->target = target;
        vector<int> t;
        dfs(0, 0, t);
        return ans;
    }

    void dfs(int s, int u, vector<int>& t) {
        if (s == target) {
            ans.push_back(t);
            return;
        }
        if (s > target) return;
        for (int i = u; i < candidates.size(); ++i) {
            int c = candidates[i];
            t.push_back(c);
            dfs(s + c, i, t);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func combinationSum(candidates []int, target int) [][]int {
	var ans [][]int

	var dfs func(s, u int, t []int)
	dfs = func(s, u int, t []int) {
		if s == target {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		if s > target {
			return
		}
		for i := u; i < len(candidates); i++ {
			c := candidates[i]
			t = append(t, c)
			dfs(s+c, i, t)
			t = t[:len(t)-1]
		}
	}

	var t []int
	dfs(0, 0, t)
	return ans
}
```

### **TypeScript**

```ts
function combinationSum(candidates: number[], target: number): number[][] {
    const n = candidates.length;
    const t: number[] = [];
    const res: number[][] = [];
    const dfs = (i: number, sum: number) => {
        if (sum > target) {
            return;
        }
        if (sum === target) {
            res.push([...t]);
            return;
        }
        for (let j = i; j < n; j++) {
            t.push(candidates[j]);
            dfs(j, sum + candidates[j]);
            t.pop();
        }
    };
    dfs(0, 0);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, count: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        if count < 0 {
            return;
        }
        if count == 0 {
            res.push(t.clone());
            return;
        }
        for j in i..candidates.len() {
            let num = candidates[j];
            t.push(num);
            Self::dfs(j, count - num, candidates, t, res);
            t.pop();
        }
    }

    pub fn combination_count(candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        let mut res = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
