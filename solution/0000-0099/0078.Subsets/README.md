# [78. 子集](https://leetcode.cn/problems/subsets)

[English Version](/solution/0000-0099/0078.Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的子集（幂集）。</p>

<p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[[],[0]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10</code></li>
	<li><code>-10 <= nums[i] <= 10</code></li>
	<li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
</ul>

## 解法

### 方法一：DFS(回溯)

我们设计一个函数 $dfs(i)$，表示从数组的第 $i$ 个元素开始搜索所有子集。函数 $dfs(i)$ 的执行逻辑如下：

-   如果 $i=n$，表示当前已经搜索结束，将当前得到的子集 $t$ 加入答案数组 $ans$ 中，然后返回；
-   否则，我们可以选择不选择当前元素，直接执行 $dfs(i+1)$；也可以选择当前元素，即把当前元素 $nums[i]$ 加入子集 $t$，然后执行 $dfs(i+1)$，注意要在执行 $dfs(i+1)$ 以后再将 $nums[i]$ 从子集 $t$ 中移除（回溯）。

在主函数中，我们调用 $dfs(0)$，即从数组的第一个元素开始搜索所有子集。最后返回答案数组 $ans$ 即可。

时间复杂度 $O(n\times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。一共有 $2^n$ 个子集，每个子集需要 $O(n)$ 的时间来构造。

<!-- tabs:start -->

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        def dfs(i: int):
            if i == len(nums):
                ans.append(t[:])
                return
            dfs(i + 1)
            t.append(nums[i])
            dfs(i + 1)
            t.pop()

        ans = []
        t = []
        dfs(0)
        return ans
```

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        dfs(i + 1);
        t.add(nums[i]);
        dfs(i + 1);
        t.remove(t.size() - 1);
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) -> void {
            if (i == nums.size()) {
                ans.push_back(t);
                return;
            }
            dfs(i + 1);
            t.push_back(nums[i]);
            dfs(i + 1);
            t.pop_back();
        };
        dfs(0);
        return ans;
    }
};
```

```go
func subsets(nums []int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if i == len(nums) {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		dfs(i + 1)
		t = append(t, nums[i])
		dfs(i + 1)
		t = t[:len(t)-1]
	}
	dfs(0)
	return
}
```

```ts
function subsets(nums: number[]): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (i === nums.length) {
            ans.push(t.slice());
            return;
        }
        dfs(i + 1);
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
    };
    dfs(0);
    return ans;
}
```

```rust
impl Solution {
    fn dfs(i: usize, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        if i == nums.len() {
            res.push(t.clone());
            return;
        }
        Self::dfs(i + 1, t, res, nums);
        t.push(nums[i]);
        Self::dfs(i + 1, t, res, nums);
        t.pop();
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = Vec::new();
        Self::dfs(0, &mut Vec::new(), &mut res, &nums);
        res
    }
}
```

<!-- tabs:end -->

### 方法二：二进制枚举

我们也可以使用二进制枚举的方法得到所有的子集。

我们可以使用 $2^n$ 个二进制数来表示 $n$ 个元素的所有子集，对于当前二进制数 $mask$，如果第 $i$ 位为 $1$，表示选择了第 $i$ 个元素，否则表示不选择第 $i$ 个元素。

时间复杂度 $O(n\times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。一共有 $2^n$ 个子集，每个子集需要 $O(n)$ 的时间来构造。

<!-- tabs:start -->

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        ans = []
        for mask in range(1 << len(nums)):
            t = [x for i, x in enumerate(nums) if mask >> i & 1]
            ans.append(t)
        return ans
```

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    t.add(nums[i]);
                }
            }
            ans.add(t);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> ans;
        for (int mask = 0; mask < 1 << n; ++mask) {
            vector<int> t;
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    t.emplace_back(nums[i]);
                }
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

```go
func subsets(nums []int) (ans [][]int) {
	n := len(nums)
	for mask := 0; mask < 1<<n; mask++ {
		t := []int{}
		for i, x := range nums {
			if mask>>i&1 == 1 {
				t = append(t, x)
			}
		}
		ans = append(ans, t)
	}
	return
}
```

```ts
function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const ans: number[][] = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t: number[] = [];
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                t.push(nums[i]);
            }
        }
        ans.push(t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
