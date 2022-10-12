# [1887. Reduction Operations to Make the Array Elements Equal](https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal)

[中文文档](/solution/1800-1899/1887.Reduction%20Operations%20to%20Make%20the%20Array%20Elements%20Equal/README.md)

## Description

<p>Given an integer array <code>nums</code>, your goal is to make all elements in <code>nums</code> equal. To complete one operation, follow these steps:</p>

<ol>
	<li>Find the <strong>largest</strong> value in <code>nums</code>. Let its index be <code>i</code> (<strong>0-indexed</strong>) and its value be <code>largest</code>. If there are multiple elements with the largest value, pick the smallest <code>i</code>.</li>
	<li>Find the <strong>next largest</strong> value in <code>nums</code> <strong>strictly smaller</strong> than <code>largest</code>. Let its value be <code>nextLargest</code>.</li>
	<li>Reduce <code>nums[i]</code> to <code>nextLargest</code>.</li>
</ol>

<p>Return <em>the number of operations to make all elements in </em><code>nums</code><em> equal</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,1,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong>&nbsp;It takes 3 operations to make all elements in nums equal:
1. largest = 5 at index 0. nextLargest = 3. Reduce nums[0] to 3. nums = [<u>3</u>,1,3].
2. largest = 3 at index 0. nextLargest = 1. Reduce nums[0] to 1. nums = [<u>1</u>,1,3].
3. largest = 3 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,<u>1</u>].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;All elements in nums are already equal.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,2,3]
<strong>Output:</strong> 4
<strong>Explanation:</strong>&nbsp;It takes 4 operations to make all elements in nums equal:
1. largest = 3 at index 4. nextLargest = 2. Reduce nums[4] to 2. nums = [1,1,2,2,<u>2</u>].
2. largest = 2 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,<u>1</u>,2,2].
3. largest = 2 at index 3. nextLargest = 1. Reduce nums[3] to 1. nums = [1,1,1,<u>1</u>,2].
4. largest = 2 at index 4. nextLargest = 1. Reduce nums[4] to 1. nums = [1,1,1,1,<u>1</u>].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        nums.sort()
        ans = cnt = 0
        for i, v in enumerate(nums[1:]):
            if v != nums[i]:
                cnt += 1
            ans += cnt
        return ans
```

```python
class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        ans = cnt = 0
        for _, v in sorted(Counter(nums).items()):
            ans += cnt * v
            cnt += 1
        return ans
```

### **Java**

```java
class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] != nums[i - 1]) {
                ++cnt;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int reductionOperations(int[] nums) {
        Map<Integer, Integer> tm = new TreeMap<>();
        for (int v : nums) {
            tm.put(v, tm.getOrDefault(v, 0) + 1);
        }
        int ans = 0, cnt = 0;
        for (int v : tm.values()) {
            ans += cnt * v;
            ++cnt;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function reductionOperations(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    let cnt = 0;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] != nums[i - 1]) {
            ++cnt;
        }
        ans += cnt;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.size(); ++i) {
            cnt += nums[i] != nums[i - 1];
            ans += cnt;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        map<int, int> m;
        for (int v : nums) ++m[v];
        int ans = 0, cnt = 0;
        for (auto [_, v] : m)
        {
            ans += cnt * v;
            ++cnt;
        }
        return ans;
    }
};
```

### **Go**

```go
func reductionOperations(nums []int) int {
	sort.Ints(nums)
	ans, cnt := 0, 0
	for i, v := range nums[1:] {
		if v != nums[i] {
			cnt++
		}
		ans += cnt
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
