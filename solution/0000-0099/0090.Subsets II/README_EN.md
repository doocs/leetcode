# [90. Subsets II](https://leetcode.com/problems/subsets-ii)

[中文文档](/solution/0000-0099/0090.Subsets%20II/README.md)

## Description

<p>Given an integer array <code>nums</code> that may contain duplicates, return <em>all possible</em> <span data-keyword="subset"><em>subsets</em></span><em> (the power set)</em>.</p>

<p>The solution set <strong>must not</strong> contain duplicate subsets. Return the solution in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,2]
<strong>Output:</strong> [[],[1],[1,2],[1,2,2],[2],[2,2]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [[],[0]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        def dfs(u, t):
            ans.append(t[:])
            for i in range(u, len(nums)):
                if i != u and nums[i] == nums[i - 1]:
                    continue
                t.append(nums[i])
                dfs(i + 1, t)
                t.pop()

        ans = []
        nums.sort()
        dfs(0, [])
        return ans
```

### **Java**

```java
class Solution {
    private List<List<Integer>> ans;
    private int[] nums;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new ArrayList<>();
        Arrays.sort(nums);
        this.nums = nums;
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, List<Integer> t) {
        ans.add(new ArrayList<>(t));
        for (int i = u; i < nums.length; ++i) {
            if (i != u && nums[i] == nums[i - 1]) {
                continue;
            }
            t.add(nums[i]);
            dfs(i + 1, t);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        vector<int> t;
        dfs(0, t, nums, ans);
        return ans;
    }

    void dfs(int u, vector<int>& t, vector<int>& nums, vector<vector<int>>& ans) {
        ans.push_back(t);
        for (int i = u; i < nums.size(); ++i) {
            if (i != u && nums[i] == nums[i - 1]) continue;
            t.push_back(nums[i]);
            dfs(i + 1, t, nums, ans);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func subsetsWithDup(nums []int) [][]int {
	sort.Ints(nums)
	var ans [][]int
	var dfs func(u int, t []int)
	dfs = func(u int, t []int) {
		ans = append(ans, append([]int(nil), t...))
		for i := u; i < len(nums); i++ {
			if i != u && nums[i] == nums[i-1] {
				continue
			}
			t = append(t, nums[i])
			dfs(i+1, t)
			t = t[:len(t)-1]
		}
	}
	var t []int
	dfs(0, t)
	return ans
}
```

### **TypeScript**

```ts
function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const t: number[] = [];
    const res: number[][] = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...t]);
            return;
        }
        t.push(nums[i]);
        dfs(i + 1);
        const num = t.pop();
        while (i < n && nums[i] == num) {
            i++;
        }
        dfs(i);
    };
    dfs(0);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(mut i: usize, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        let n = nums.len();
        if i == n {
            res.push(t.clone());
            return;
        }
        t.push(nums[i]);
        Self::dfs(i + 1, t, res, nums);
        let num = t.pop().unwrap();
        while i < n && num == nums[i] {
            i += 1;
        }
        Self::dfs(i, t, res, nums);
    }

    pub fn subsets_with_dup(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let mut res = Vec::new();
        Self::dfs(0, &mut Vec::new(), &mut res, &nums);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
