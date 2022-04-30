# [2248. Intersection of Multiple Arrays](https://leetcode.com/problems/intersection-of-multiple-arrays)

[中文文档](/solution/2200-2299/2248.Intersection%20of%20Multiple%20Arrays/README.md)

## Description

Given a 2D integer array <code>nums</code> where <code>nums[i]</code> is a non-empty array of <strong>distinct</strong> positive integers, return <em>the list of integers that are present in <strong>each array</strong> of</em> <code>nums</code><em> sorted in <strong>ascending order</strong></em>.

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [[<u><strong>3</strong></u>,1,2,<u><strong>4</strong></u>,5],[1,2,<u><strong>3</strong></u>,<u><strong>4</strong></u>],[<u><strong>3</strong></u>,<u><strong>4</strong></u>,5,6]]
<strong>Output:</strong> [3,4]
<strong>Explanation:</strong> 
The only integers present in each of nums[0] = [<u><strong>3</strong></u>,1,2,<u><strong>4</strong></u>,5], nums[1] = [1,2,<u><strong>3</strong></u>,<u><strong>4</strong></u>], and nums[2] = [<u><strong>3</strong></u>,<u><strong>4</strong></u>,5,6] are 3 and 4, so we return [3,4].</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [[1,2,3],[4,5,6]]
<strong>Output:</strong> []
<strong>Explanation:</strong> 
There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(nums[i].length) &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i][j] &lt;= 1000</code></li>
	<li>All the values of <code>nums[i]</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def intersection(self, nums: List[List[int]]) -> List[int]:
        cnt = [0] * 1001
        for num in nums:
            for v in num:
                cnt[v] += 1
        n = len(nums)
        return [i for i, v in enumerate(cnt) if v == n]
```

### **Java**

```java
class Solution {
    public List<Integer> intersection(int[][] nums) {
        int[] cnt = new int[1001];
        for (int[] num : nums) {
            for (int i : num) {
                cnt[i]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            if (cnt[i] == nums.length) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function intersection(nums: number[][]): number[] {
    const n = nums.length;
    let ans = nums[0];
    for (let i = 1; i < n && ans.length; i++) {
        const cur = new Set(nums[i]);
        // get intersect
        ans = ans.filter(v => cur.has(v));
    }
    return ans.sort((a, b) => a - b);
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> intersection(vector<vector<int>>& nums) {
        vector<int> cnt(1001);
        for (auto& num : nums)
            for (int v : num)
                ++cnt[v];
        int n = nums.size();
        vector<int> ans;
        for (int i = 1; i < 1001; ++i)
            if (cnt[i] == n)
                ans.push_back(i);
        return ans;
    }
};
```

### **Go**

```go
func intersection(nums [][]int) []int {
	cnt := make([]int, 1001)
	for _, num := range nums {
		for _, v := range num {
			cnt[v]++
		}
	}
	var ans []int
	for i, v := range cnt {
		if v == len(nums) {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
