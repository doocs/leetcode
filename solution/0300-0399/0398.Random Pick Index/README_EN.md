# [398. Random Pick Index](https://leetcode.com/problems/random-pick-index)

[中文文档](/solution/0300-0399/0398.Random%20Pick%20Index/README.md)

## Description

<p>Given an integer array <code>nums</code> with possible <strong>duplicates</strong>, randomly output the index of a given <code>target</code> number. You can assume that the given target number must exist in the array.</p>

<p>Implement the <code>Solution</code> class:</p>

<ul>
	<li><code>Solution(int[] nums)</code> Initializes the object with the array <code>nums</code>.</li>
	<li><code>int pick(int target)</code> Picks a random index <code>i</code> from <code>nums</code> where <code>nums[i] == target</code>. If there are multiple valid i&#39;s, then each index should have an equal probability of returning.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;, &quot;pick&quot;, &quot;pick&quot;, &quot;pick&quot;]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
<strong>Output</strong>
[null, 4, 0, 2]

<strong>Explanation</strong>
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>target</code> is an integer from <code>nums</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>pick</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def __init__(self, nums: List[int]):
        self.nums = nums

    def pick(self, target: int) -> int:
        n = ans = 0
        for i, v in enumerate(self.nums):
            if v == target:
                n += 1
                x = random.randint(1, n)
                if x == n:
                    ans = i
        return ans


# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.pick(target)
```

### **Java**

```java
class Solution {
    private int[] nums;
    private Random random = new Random();

    public Solution(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int n = 0, ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                ++n;
                int x = 1 + random.nextInt(n);
                if (x == n) {
                    ans = i;
                }
            }
        }
        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nums;

    Solution(vector<int>& nums) {
        this->nums = nums;
    }

    int pick(int target) {
        int n = 0, ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == target) {
                ++n;
                int x = 1 + rand() % n;
                if (n == x) ans = i;
            }
        }
        return ans;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * int param_1 = obj->pick(target);
 */
```

### **Go**

```go
type Solution struct {
	nums []int
}

func Constructor(nums []int) Solution {
	return Solution{nums}
}

func (this *Solution) Pick(target int) int {
	n, ans := 0, 0
	for i, v := range this.nums {
		if v == target {
			n++
			x := 1 + rand.Intn(n)
			if n == x {
				ans = i
			}
		}
	}
	return ans
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(nums);
 * param_1 := obj.Pick(target);
 */
```

### **...**

```

```

<!-- tabs:end -->
