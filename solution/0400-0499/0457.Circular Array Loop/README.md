# [457. 环形数组是否存在循环](https://leetcode.cn/problems/circular-array-loop)

[English Version](/solution/0400-0499/0457.Circular%20Array%20Loop/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>存在一个不含 <code>0</code> 的<strong> 环形 </strong>数组&nbsp;<code>nums</code> ，每个 <code>nums[i]</code> 都表示位于下标 <code>i</code> 的角色应该向前或向后移动的下标个数：</p>

<ul>
	<li>如果 <code>nums[i]</code> 是正数，<strong>向前</strong>（下标递增方向）移动 <code>|nums[i]|</code> 步</li>
	<li>如果&nbsp;<code>nums[i]</code> 是负数，<strong>向后</strong>（下标递减方向）移动 <code>|nums[i]|</code> 步</li>
</ul>

<p>因为数组是 <strong>环形</strong> 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。</p>

<p>数组中的 <strong>循环</strong> 由长度为 <code>k</code> 的下标序列 <code>seq</code> 标识：</p>

<ul>
	<li>遵循上述移动规则将导致一组重复下标序列 <code>seq[0] -&gt; seq[1] -&gt; ... -&gt; seq[k - 1] -&gt; seq[0] -&gt; ...</code></li>
	<li>所有 <code>nums[seq[j]]</code> 应当不是 <strong>全正</strong> 就是 <strong>全负</strong></li>
	<li><code>k &gt; 1</code></li>
</ul>

<p>如果 <code>nums</code> 中存在循环，返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,-1,1,2,2]
<strong>输出：</strong>true
<strong>解释：</strong>存在循环，按下标 0 -&gt; 2 -&gt; 3 -&gt; 0 。循环长度为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,2]
<strong>输出：</strong>false
<strong>解释：</strong>按下标 1 -&gt; 1 -&gt; 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>nums = [-2,1,-1,-2,-2]
<strong>输出：</strong>false
<strong>解释：</strong>按下标 1 -&gt; 2 -&gt; 1 -&gt; ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
所有 nums[seq[j]] 应当不是全正就是全负。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums[i] != 0</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能设计一个时间复杂度为 <code>O(n)</code> 且额外空间复杂度为 <code>O(1)</code> 的算法吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

快慢指针。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
