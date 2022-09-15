# [457. Circular Array Loop](https://leetcode.com/problems/circular-array-loop)

[中文文档](/solution/0400-0499/0457.Circular%20Array%20Loop/README.md)

## Description

<p>You are playing a game involving a <strong>circular</strong> array of non-zero integers <code>nums</code>. Each <code>nums[i]</code> denotes the number of indices forward/backward you must move if you are located at index <code>i</code>:</p>

<ul>
	<li>If <code>nums[i]</code> is positive, move <code>nums[i]</code> steps <strong>forward</strong>, and</li>
	<li>If <code>nums[i]</code> is negative, move <code>nums[i]</code> steps <strong>backward</strong>.</li>
</ul>

<p>Since the array is <strong>circular</strong>, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.</p>

<p>A <strong>cycle</strong> in the array consists of a sequence of indices <code>seq</code> of length <code>k</code> where:</p>

<ul>
	<li>Following the movement rules above results in the repeating index sequence <code>seq[0] -&gt; seq[1] -&gt; ... -&gt; seq[k - 1] -&gt; seq[0] -&gt; ...</code></li>
	<li>Every <code>nums[seq[j]]</code> is either <strong>all positive</strong> or <strong>all negative</strong>.</li>
	<li><code>k &gt; 1</code></li>
</ul>

<p>Return <code>true</code><em> if there is a <strong>cycle</strong> in </em><code>nums</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0457.Circular%20Array%20Loop/images/img1.jpg" style="width: 402px; height: 289px;" />
<pre>
<strong>Input:</strong> nums = [2,-1,1,2,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
We can see the cycle 0 --&gt; 2 --&gt; 3 --&gt; 0 --&gt; ..., and all of its nodes are white (jumping in the same direction).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0457.Circular%20Array%20Loop/images/img2.jpg" style="width: 402px; height: 390px;" />
<pre>
<strong>Input:</strong> nums = [-1,-2,-3,-4,-5,6]
<strong>Output:</strong> false
<strong>Explanation:</strong> The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
The only cycle is of size 1, so we return false.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0457.Circular%20Array%20Loop/images/img3.jpg" style="width: 497px; height: 242px;" />
<pre>
<strong>Input:</strong> nums = [1,-1,5,1,4]
<strong>Output:</strong> true
<strong>Explanation:</strong> The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
We can see the cycle 0 --&gt; 1 --&gt; 0 --&gt; ..., and while it is of size &gt; 1, it has a node jumping forward and a node jumping backward, so <strong>it is not a cycle</strong>.
We can see the cycle 3 --&gt; 4 --&gt; 3 --&gt; ..., and all of its nodes are white (jumping in the same direction).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums[i] != 0</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it in <code>O(n)</code> time complexity and <code>O(1)</code> extra space complexity?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def circularArrayLoop(self, nums: List[int]) -> bool:
        n = len(nums)

        def next(i):
            return (i + nums[i] % n + n) % n

        for i in range(n):
            if nums[i] == 0:
                continue
            slow, fast = i, next(i)
            while nums[slow] * nums[fast] > 0 and nums[slow] * nums[next(fast)] > 0:
                if slow == fast:
                    if slow != next(slow):
                        return True
                    break
                slow, fast = next(slow), next(next(fast))
            j = i
            while nums[j] * nums[next(j)] > 0:
                nums[j] = 0
                j = next(j)
        return False
```

### **Java**

```java
class Solution {
    private int n;
    private int[] nums;

    public boolean circularArrayLoop(int[] nums) {
        n = nums.length;
        this.nums = nums;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = next(i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(slow)) {
                        return true;
                    }
                    break;
                }
                slow = next(slow);
                fast = next(next(fast));
            }
            int j = i;
            while (nums[j] * nums[next(j)] > 0) {
                nums[j] = 0;
                j = next(j);
            }
        }
        return false;
    }

    private int next(int i) {
        return (i + nums[i] % n + n) % n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool circularArrayLoop(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (!nums[i]) continue;
            int slow = i, fast = next(nums, i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) return true;
                    break;
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            int j = i;
            while (nums[j] * nums[next(nums, j)] > 0) {
                nums[j] = 0;
                j = next(nums, j);
            }
        }
        return false;
    }

    int next(vector<int>& nums, int i) {
        int n = nums.size();
        return (i + nums[i] % n + n) % n;
    }
};
```

### **Go**

```go
func circularArrayLoop(nums []int) bool {
	for i, num := range nums {
		if num == 0 {
			continue
		}
		slow, fast := i, next(nums, i)
		for nums[slow]*nums[fast] > 0 && nums[slow]*nums[next(nums, fast)] > 0 {
			if slow == fast {
				if slow != next(nums, slow) {
					return true
				}
				break
			}
			slow, fast = next(nums, slow), next(nums, next(nums, fast))
		}
		j := i
		for nums[j]*nums[next(nums, j)] > 0 {
			nums[j] = 0
			j = next(nums, j)
		}
	}
	return false
}

func next(nums []int, i int) int {
	n := len(nums)
	return (i + nums[i]%n + n) % n
}
```

### **...**

```

```

<!-- tabs:end -->
